import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;




    public class CollectionReader
    {
        public static void ReadFile() throws IOException
        {



        }

        public DataInputStream reader;


        public CollectionReader(String filename) throws IOException
        {
            this(new FileInputStream(filename));
        }

        public CollectionReader(InputStream source)
        {
            this.reader = new DataInputStream(source);
        }

        // --- Primitive values ---

        public byte readByte() throws IOException
        {
            // 1 byte
            return this.reader.readByte();
        }



        public int readInt() throws IOException
        {
            // 4 bytes, little endian
            byte[] bytes = new byte[4];
            this.reader.readFully(bytes);
            ByteBuffer bb = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN);
            return bb.getInt();
        }


        public int readULEB128() throws IOException
        {
            // variable bytes, little endian
            // MSB says if there will be more bytes. If cleared,
            // that byte is the last.
            int value = 0;
            for (int shift = 0; shift < 32; shift += 7)
            {
                byte b = this.reader.readByte();
                value |= ((int) b & 0x7F) << shift;

                if (b >= 0) return value; // MSB is zero. End of value.
            }
            throw new IOException("ULEB128 too large");
        }







        public String readString() throws IOException
        {
            // variable length
            // 00 = empty string
            // 0B <length> <char>* = normal string
            // <length> is encoded as an LEB, and is the byte length of the rest.
            // <char>* is encoded as UTF8, and is the string content.
            byte kind = this.reader.readByte();
            if (kind == 0) return "";
            if (kind != 11)
            {
                throw new IOException(String.format("String format error: Expected 0x0B or 0x00, found 0x%02X", (int) kind & 0xFF));
            }
            int length = readULEB128();
            if (length == 0) return "";
            byte[] utf8bytes = new byte[length];
            this.reader.readFully(utf8bytes);
            return new String(utf8bytes, "UTF-8");
        }



        // --- Composite structures ---

        public CollectionDB readCollectionDB() throws IOException
        {
            CollectionDB result = new CollectionDB();
            result.version = readInt();
            result.collectionCount = readInt();
            result.collections = new ArrayList<CollectionItem>(result.collectionCount);
            for (int i = 0; i < result.collectionCount; i++)
            {
                CollectionItem item = readCollectionItem();
                result.collections.add(item);
            }

            return result;
        }

        public CollectionItem readCollectionItem() throws IOException
        {
            CollectionItem item = new CollectionItem();
            item.name = readString();
            int count = readInt();
            item.md5Hashes = new ArrayList<String>(count);
            for (int i = 0; i < count; i++)
            {
                String md5Hash = readString();
                item.md5Hashes.add(md5Hash);
            }
            return item;
        }


        public class CollectionDB
        {
            public int version; // 20150203
            public List<CollectionItem> collections;
            public int collectionCount;
        }

        public class CollectionItem
        {
            public String name;
            public List<String> md5Hashes;
        }









}

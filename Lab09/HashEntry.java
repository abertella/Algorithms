public class HashEntry {
      private long key;
      private String value;

      HashEntry(long key, String value) {
            this.key = key;
            this.value = value;
      }     
      
      public long getKey() {
            return key;
      }

      public String  getValue() {
            return value;
      }
      
      public void setValue(String val) {
            this.value = val;
      }
}

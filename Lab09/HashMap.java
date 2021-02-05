public class HashMap {
  private final static int TABLE_SIZE = 100;
  private int size;
  private final static int TIMEOUT = 1000;

  HashEntry[] table;

   HashMap (int size) {
     this.table = new HashEntry[size];
     this.size = size;
  }

   HashMap() {
      this.table = new HashEntry[TABLE_SIZE];
      this.size = TABLE_SIZE;
  }

  public String get(long key) {
     int tcount = 0;
     int index = (int)(key % size);
     while(table[index].getKey() != key) {
        index = (7 * index + 1) % size;
        tcount++;
         if(tcount == TIMEOUT) {
           return "Key not found";
         }
     }
     return table[index].getValue();
  }

  public void put(long key, String value) {
     int tcount = 0;
     int index = (int)(key % size);
     while(table[index] != null) {
        if(table[index].getKey() == key) {
           break;
        }
        index = (7 * index + 1) % size;
        tcount++;
        if(tcount == TIMEOUT) {
           return;
        }
     }
     table[index]= new HashEntry(key, value);
  }

  public void linearPut(long key, String value){
     int index = (int)(key % size);
     int count = 1;
     int retindex = index;
     int tcount = 0;
     while(table[retindex] != null) {
         if(table[retindex].getKey() == key) {
           break;
        }
        retindex = (index + count) % size;
        count++;
        tcount++;
        if(tcount == TIMEOUT) {
           return;
        }
     }
     table[retindex]= new HashEntry(key, value);
  }

  public String linearGet(long key) {
    int index = (int)(key % size);
    int count = 1;
    int retindex = index;
    int tcount = 0;
    while(table[retindex].getKey() != key) {
       retindex = (index + count) % size;
       count++;
       tcount++;
       if(tcount == TIMEOUT) {
          return "Key not found";
       }
     }
   return table[retindex].getValue();
  }

  public void quadraticPut(long key, String value){
     int index = (int)(key % size);
     int count = 1;
     int retindex = index;
     int tcount = 0;
     while(table[retindex] != null) {
         if(table[retindex].getKey() == key) {
           break;
        }
        retindex = (index + (count*count)) % size;
        count++;
        tcount++;
        if(tcount == TIMEOUT) {
           return;
        }
     }
     table[retindex]= new HashEntry(key, value);
  }

  public String quadraticGet(long key){
    int index = (int)(key % size);
    int count = 1;
    int retindex = index;
    int tcount = 0;
    while(table[retindex].getKey() != key) {
       retindex = (index + (count*count)) % size;
       count++;
       tcount++;
       if(tcount == TIMEOUT) {
          return "Key not found";
       }
     }
   return table[retindex].getValue();
  }
  public void printMap() {
     for(int i = 0; i < size; i++) {
        if(table[i] != null) {
         System.out.format("%d\t%d\t%s\n", i, table[i].getKey(), table[i].getValue());
        }
        else {
           System.out.format("%d\tnull\n", i);
        }
     }
  }

  public String linearSearch(long key) {
     int index = 0;
      while(index < table.length){
         if(table[index].getKey() == key) {
            return(table[index].getValue());
         }
      }
      return "Key not found";
  }
}

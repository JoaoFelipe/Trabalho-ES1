package codes;

import store.Store;

public class Code {
    
    private static final char[] CHARLIST = {
      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
//      'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
//      'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
//      'U', 'V', 'W', 'X', 'Y', 'Z', 
      'a', 'b', 'c', 'd',
      'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
      'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
      'y', 'z'
    };
    
    private int value;
    private String key;
    private boolean activated = false;
    
    public Code(int value) {
        this.value = value;
        do {
            this.key = Code.generateKey(value);
        } while (Store.getInstance().hasCode(this.key));
    }
    
    public Code(String key) {
        this.key = key;
        this.value = Integer.parseInt(key.split("-")[0]);
    }

    public static String generateKey(int value) {
        String result = value + "";
        for (int j = 0; j < 3; j++) {
            result += '-';
            String temp = ("" + Math.random()).substring(2);
            for (int i = 0; i < 4; i++) {
                result += CHARLIST[Integer.parseInt(temp.substring(i*2, (i+1)*2)) % CHARLIST.length];
            }
        }
        return result;
    }
    
    public void activate() {
        this.setActivated(true);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Code)) {
            return false;
        }

        final Code other = (Code) obj;
        if (this.getValue() != other.getValue()) {
            return false;
        }
        if ((this.getKey() == null) ? (other.getKey() != null) : !this.getKey().equals(other.getKey())) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return this.value + 31*this.key.hashCode();
    }
    
    public String getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }
    
    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package credits;

import java.util.Arrays;


/**
 *
 * @author Joao
 */
public class Credit {
    
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
    private String code;
    private boolean activated = false;
    
    public Credit(int value) {
        this.value = value;
        do {
            this.code = Credit.generateCode(value);
        } while (Credits.getInstance().hasCredit(this.code));
    }
    
    public Credit(String code) {
        this.code = code;
        this.value = Integer.parseInt(code.split("-")[0]);
    }

    public static String generateCode(int value) {
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
    
    public String getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }
    
    public boolean isActive() {
        return activated;
    }
    
    public void activate() {
        activated = true;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Credit)) {
            return false;
        }

        final Credit other = (Credit) obj;
        if (this.value != other.value) {
            return false;
        }
        if ((this.code == null) ? (other.code != null) : !this.code.equals(other.code)) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return this.value + 31*this.code.hashCode();
    }
    
    public Object[] tuple() {
        return Arrays.asList(this.code, this.value).toArray();
    }
    
}

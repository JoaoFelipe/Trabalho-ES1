package credits;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class Credits {
    
    private static Credits instance;
    private HashSet<Credit> list;
    
    private Credits() {
        list = new HashSet<Credit>();
    }
    
    public static Credits getInstance() {
        if (instance == null) {
            instance = new Credits();
        }
        return instance;
    }
    
    public static void eraseInstance() {
        instance = null;
    }
    
    public boolean hasCredit(String code) {
        Credit credit = new Credit(code);
        return this.getList().contains(credit);
    }
    
    public boolean register(Credit credit) {
        return this.getList().add(credit);
    }
    
    public void generate(int count, int value) {
        for (int i = 0; i < count; i++) {
            this.register(new Credit(value));
        }
    }
    
    public Credit find(String code) {
        for (Credit credit : this.getList()) {
            if (credit.getCode().equals(code) && !credit.isActivated()) {
                return credit;
            }
        }
        return null;
    }
    
    public Credit activate(String code) throws Exception {
        Credit credit = this.find(code);
        if (credit == null) {
            throw new Exception("O código digitado não existe");
        }
        credit.activate();
        return credit;
    }

    public HashSet<Credit> getList() {
        return list;
    }
    
}

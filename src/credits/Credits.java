/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package credits;

import java.util.HashSet;

/**
 *
 * @author Joao
 */
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
        return list.contains(credit);
    }
    
    public boolean register(Credit credit) {
        return list.add(credit);
    }
    
    public int count() {
        return list.size();
    }

    public void generate(int count, int value) {
        for (int i = 0; i < count; i++) {
            this.register(new Credit(value));
        }
    }
    
    public Credit find(String code) {
        for (Credit credit : list) {
            if (credit.getCode().equals(code) && !credit.isActive()) {
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
    
    
}

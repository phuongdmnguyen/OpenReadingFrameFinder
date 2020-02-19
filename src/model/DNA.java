package model;

import exceptions.InvalidDNABase;

import java.util.ArrayList;

public class DNA {
    private ArrayList<String> sequence;
    // sequence can only include one of A, C, G T

    public DNA() {
        sequence = new ArrayList<>();
    }

    public String getSequenceAsString() {
        String a = "";
        for (String i : sequence) {
            a = a + i;
        }
        return a;
    }

    public void addBase(String s) throws InvalidDNABase {
        if (s == "A" || s == "C" || s == "G" || s == "T") {
            sequence.add(s);
        } else {
            throw new InvalidDNABase();
        }
    }

    public void mutateAtPos(int i, String base) {
        sequence.set(i,base);
    }

    public void removeBaseAtIndex(int i) {
        sequence.remove(i);
    }

    public int countPyrimidines() {
        int pyr = 0;
        for (String b : sequence) {
            if (b == "C" || b == "T") {
                pyr++;
            }
        }
        return pyr;
    }

    public int countPurines() {
        int pur = 0;
        for (String b : sequence) {
            if (b == "A" || b == "G") {
                pur++;
            }
        }
        return pur;
    }

    public RNA transcribe() {
        RNA rna = new RNA();
        for (String b : sequence) {
            if (b == "A") {
                rna.addBase("U");
            } else if (b == "T") {
                rna.addBase("A");
            } else if (b == "G") {
                rna.addBase("C");
            } else if (b == "C") {
                rna.addBase("G");
            }
        }
        return rna;
    }
}

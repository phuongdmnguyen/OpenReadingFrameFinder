package model;

import java.util.ArrayList;
import java.util.Objects;

public class RNA {
    private ArrayList<String> sequence;
    private ArrayList<String> codons;
    private ArrayList<String> aminoacidsequence;

    public RNA() {
        sequence = new ArrayList<>();
        codons = new ArrayList<>();
        aminoacidsequence = new ArrayList<>();
    }

    public ArrayList<String> getSequence() {
        return sequence;
    }

    public ArrayList<String> getCodons() {
        return codons;
    }

    public ArrayList<String> getAminoacidsequence() { return aminoacidsequence;}

    public void addBase(String b) {
        sequence.add(b);
    }

    public void removeBaseAtIndex(int i) {
        sequence.remove(i);
    }

    public void removeCodonAtIndex(int i) {
        codons.remove(i);
    }

    public void addCodons(String c) { codons.add(c);}

    // REQUIRES: sequence length is a multiple of 3 and is > 0
    // MODIFIES: codons
    // EFFECTS: split RNA bases into codons (string group of 3)
    public void groupCodon() {
        int counter = 0;
        int index = 0;
        for (String i : sequence) {
            if (counter == 3) {
                counter = 1;
                index++;
                codons.add(i);
            } else {
                if (index >= codons.size()) {
                    codons.add(index, i);
                    counter++;
                } else {
                    String a = codons.get(index);
                    codons.set(index, a + i);
                    counter++;
                }
            }
        }
    }

    /** get the Index of first base based on the 3-base condon
     * REQUIRES: codon must be a string of 3 bases
     * @param codon
     */
    public int getIndexFirstBase(String codon) {
        String firstBase = codon.substring(0,1);
        if (firstBase.equals("U")) {
            return 0;
        } else if (firstBase.equals("C")) {
            return 1;
        } else if (firstBase.equals("A")) {
            return 2;
        } else {
            return 3;
        }
    }

    public int getIndexSecondBase(String codon) {
        String secondBase = codon.substring(1,2);
        if (secondBase.equals("U")) {
            return 1;
        } else if (secondBase.equals("C")) {
            return 2;
        } else if (secondBase.equals("A")) {
            return 3;
        } else {
            return 4;
        }
    }

    public int getIndexThirdBase(String codon) {
        String thirdBase = codon.substring(2);
        if (thirdBase.equals("U")) {
            return 0;
        } else if (thirdBase.equals("C")) {
            return 1;
        } else if (thirdBase.equals("A")) {
            return 2;
        } else {
            return 3;
        }
    }

    public String translateCodon(String codon) {
        int firstIndex = getIndexFirstBase((codon));
        int secondIndex = getIndexSecondBase(codon);
        int thirdIndex = getIndexThirdBase(codon);
        int codonIndex = 16 * firstIndex + secondIndex + 4 * thirdIndex;
        return codon_table_3_letter[codonIndex - 1];
    }

    public void translateCodons() {
        for (String c : codons) {
            aminoacidsequence.add(translateCodon(c));
        }
    }

    /** This is the codon_table being used to translate a codon into an amino acid
     * that is abbreviated in its 3-letter code.
     * Has four blocks, representing different first base.
     * Blocks have first base in this order (top to bottom): U, C, A G
     *Each row in each block has a different second base (top to bottom): U C A G
     * Each column in each block has a different third base (left to right): U C A G
     */
    final static String[] codon_table_3_letter = {
         //2nd U      C     A       G        3rd
            "Phe", "Ser", "Tyr", "Cys",    // U
            "Phe", "Ser", "Tyr", "Cys",    // C
            "Leu", "Ser", "Stop", "Stop",  // A
            "Leu", "Ser", "Stop", "Trp",   // G

            "Leu", "Pro", "His", "Arg",    // U
            "Leu", "Pro", "His", "Arg",    // C
            "Leu", "Pro", "Gln", "Arg",    // A
            "Leu", "Pro", "Gln", "Arg",    // G

            "Ile", "Thr", "Asn", "Ser",    // U
            "Ile", "Thr", "Asn", "Ser",    // C
            "Ile", "Thr", "Lys", "Arg",    // A
            "Met", "Thr", "Lys", "Arg",    // G

            "Val", "Ala", "Asp", "Gly",    // U
            "Val", "Ala", "Asp", "Gly",    // C
            "Val", "Ala", "Glu", "Gly",    // A
            "Val", "Ala", "Glu", "Gly",    // G

    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RNA rna = (RNA) o;
        return sequence.equals(rna.sequence) &&
                codons.equals(rna.codons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sequence, codons);
    }
}

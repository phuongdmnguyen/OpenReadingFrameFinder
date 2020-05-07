package model;

import java.util.HashMap;

public class ORFFinder {

    private String dnaSequence;
    private String rnaSequence;
    private String aminoAcid;

    public ORFFinder() {
        dnaSequence = "";
        rnaSequence = "";
        aminoAcid = "";
    }

    public ORFFinder(String dnaSequence) {
        this.dnaSequence = dnaSequence;
    }

    public String getDnaSequence() {
            return dnaSequence;
        }

        public void setDnaSequence(String dnaSequence) {
            this.dnaSequence = dnaSequence;
        }

        public String getRnaSequence() {
            return rnaSequence;
        }

        public void setRnaSequence(String rnaSequence) {
            this.rnaSequence = rnaSequence;
        }

        public String getAminoAcid() {
            return aminoAcid;
        }

        public void setAminoAcid(String aminoAcid) {
            this.aminoAcid = aminoAcid;
        }

    public void transcribeDNA() {
        for (int i = 0; i < dnaSequence.length(); i++) {
            switch (dnaSequence.charAt(i)) {
                case 'A':
                    rnaSequence = getRnaSequence() + 'U';
                    break;
                case 'T':
                    rnaSequence = getRnaSequence() + 'A';
                    break;
                case 'C':
                    rnaSequence = getRnaSequence() + 'G';
                    break;
                case 'G':
                    rnaSequence = getRnaSequence() + 'C';
                    break;
            }
        }
    }

    public int getBaseIndex(char base) {
        switch (base) {
            case 'U':
                return 0;
            case 'C':
                return 1;
            case 'A':
                return 2;
            case 'G':
                return 3;
        }
        return -1;
    }


    /**
     * Look for an open reading frame in RNA
     * check if there exists a sequence that starts with a start codon
     * and ends with a stop codon within an rna sequence
     *
     * method: no skip, skip 1, skip 2
     * @return A HashMap of possible ORF in strings, empty string if no ORF is found
     */
    public HashMap<String, String> findORF() {
        HashMap<String, String> results = new HashMap<>();
        results.put("noSkip", ORFNoSkip());
        results.put("oneSkip", ORF1Skip());
        results.put("twoSkip", ORF2Skip());
        return results;
    }

    /**
     * @return ORF no skip of original sequence, empty string otherwise
     */
    private String ORFNoSkip() {
        return ORFabstract(rnaSequence);
    }

    /**
     * @return ORF with skipping 1st base of original sequence, empty string otherwise
     */
    private String ORF1Skip() {
        String sequence = rnaSequence.substring(1);
        return ORFabstract(sequence);
    }

    /**
     * @return ORF 2 skips of original sequence, empty string otherwise
     */
    private String ORF2Skip() {
        String sequence = rnaSequence.substring(2);
        return ORFabstract(sequence);
    }

    /**
     * Abstract function for ORF finder
     */
    private String ORFabstract(String sequence) {
        String startCodon = "";
        int indexStart = 0; // index of last base in start codon
        int indexSto = 0;
        boolean found = false;
        for (int i = 0; i < sequence.length(); i++) {
            if (i % 3 == 0 || i % 3 == 1) {
                startCodon += sequence.charAt(i);
            }
            if (i % 3 == 2) {
                startCodon += sequence.charAt(i);
                if (isStartCodon(startCodon)) {
                    indexStart = i;
                    int indexStop = checkORF(sequence);
                    indexSto = indexStop;
                    if (isStopCodon(sequence.substring(indexStop-2, indexStop + 1))) {
                        found = true;
                        break;
                    }
                }
                startCodon = "";
            }
        }
        if (found) {
            return sequence.substring(indexStart-2, indexSto + 1);
        }
        else {
            return "";
        }
    }

    /**
     * Once we've found the start codon for the different ORF
     * this method checks if this is sequence has a valid stop codon
     * @param sequence
     * @return the index of the last base of the stop codon
     */
    private int checkORF(String sequence) {
        String curr = "";
        boolean hasStop = false;
        int baseIndex = -1;
        for (int i = 0; i < sequence.length(); i++) {
            switch (i % 3) {
                case 0:
                case 1:
                    curr += sequence.charAt(i);
                    break;
                case 2:
                    curr += sequence.charAt(i);
                    if (isStopCodon(curr)) {
                        hasStop = true;
                        baseIndex = i;
                    }
                    curr = "";
                    break;
                }
             if (hasStop) {
                 break;
             }
        }
        return baseIndex;
    }

    public void translateRNA() {
        String codon = "";
        int baseIndex = 0;
        for (int i = 0; i < rnaSequence.length(); i++) {
            if (i % 3 == 0) {
                baseIndex = getBaseIndex(rnaSequence.charAt(i)) * 16;
            }
            if (i % 3 == 1) {
                baseIndex += getBaseIndex(rnaSequence.charAt(i));
            }
            if (i % 3 == 2) {
                baseIndex += getBaseIndex(rnaSequence.charAt(i)) * 4;
                aminoAcid = getAminoAcid() + codon_table_1_letter[baseIndex];
            }
        }
    }

    /**
     *
     * @param codon which is of type String
     * @return true if codon is a start codon
     */
    private boolean isStartCodon(String codon) {
        return codon.equals("AUG");
    }

    /**
     *
     * @param codon which is of type String
     * @return true if codon is a start codon
     */
    private boolean isStopCodon(String codon) {
        return codon.equals("UAA") || codon.equals("UAG")|| codon.equals("UGA");
    }


    /** This is the codon_table being used to translate a codon into an amino acid
     * that is abbreviated in its 3-letter code.
     * Has four blocks, representing different first base.
     * Blocks have first base in this order (top to bottom): U, C, A G
     *Each row in each block has a different second base (top to bottom): U C A G
     * Each column in each block has a different third base (left to right): U C A G
     */
    final static String[] codon_table_3_letter = {
            // U      C     A       G        3rd
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

    /** This is the codon_table being used to translate a codon into an amino acid
     * that is abbreviated in its 1-letter code.
     * Has four blocks, representing different first base.
     * Blocks have first base in this order (top to bottom): U, C, A G
     *Each row in each block has a different second base (top to bottom): U C A G
     * Each column in each block has a different third base (left to right): U C A G
     */
    final static String[] codon_table_1_letter = {
            // U      C     A       G        3rd
            "F", "S", "Y", "C",    // U
            "F", "S", "Y", "C",    // C
            "L", "S", "Stop", "Stop",  // A
            "L", "S", "Stop", "W",   // G

            "L", "P", "H", "R",    // U
            "L", "P", "H", "R",    // C
            "L", "P", "Q", "R",    // A
            "L", "P", "Q", "R",    // G

            "I", "T", "N", "S",    // U
            "I", "T", "N", "S",    // C
            "I", "T", "K", "R",    // A
            "M", "T", "K", "R",    // G

            "V", "A", "D", "G",    // U
            "V", "A", "D", "G",    // C
            "V", "A", "E", "G",    // A
            "V", "A", "E", "G",    // G

    };
}


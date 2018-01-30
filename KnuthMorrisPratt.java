

class KnuthMorrisPratt {
    private String P;
    private String T;
    private int m; //Musterlange
    private int n; //Textlange
    private int i;
    private int j;
    private int[] next;
    private int[] next2;

    public KnuthMorrisPratt(String P, String T) {
        this.P = P;
        this.T = T;
        this.m = P.length();
        this.n = T.length();
        this.next = new int[m];
        this.next2 = new int[m];
    }

    public void mach_next_tabelle() {
        i = 0;
        j = -1;
        next[0] = -1;
        while (i < m-1) {
            if ((j==-1) || (P.charAt(i) == P.charAt(j))) {
                i++;
                j++;
                next[i] = j;                 
            } else j = next[j];
        }
        System.out.println("\nder next wert bedeutet: wieviele Stelle am Anfang des Musters sind genau wie das Submuster links von dieser Stelle");
        System.out.println("______ next tabelle ______");
        for (i=0; i<m; i++) {
            System.out.print(P.charAt(i)+":"+next[i]+" ");
        }
        System.out.println("\n");
        System.out.println("setze next'[j] auf next[next[j]], wenn P[j] == P[next[j]]");
        System.out.println("wenn diese beide chars gleich sind (P[j],P[next[j]]), wir wissen dass die Muster da nicht passen wird, suchen wir die nachste match");
        i = 0;
        j = -1;
        next2[0] = -1;
        while (i < m-1) {
            if ((j==-1) || (P.charAt(i) == P.charAt(j))) {
                //System.out.println("j==-1 || char(i)==char(j): "+i+" "+j);
                i++;
                j++;
                //System.out.println("i++j++: "+i+" "+j);
                if (P.charAt(i) == P.charAt(j)) {
                    next2[i] = next2[j];
                    //System.out.println("char(i)==char(j): "+P.charAt(i)+" "+P.charAt(j));
                }
                else next2[i] = j;
            } else { 
                j = next2[j];
                //System.out.println("j=next2[j]: "+i+" "+j);
            }
        }
        System.out.println("______ next' ______");
        for (i=0; i<m; i++) {
            System.out.print(P.charAt(i)+":"+next2[i]+" ");
        }
        System.out.println("\n");
    }

    public int vergleich_an_der_stelle(int stelle) {
        System.out.println(T);
        int counter;
        int leerzeichen = stelle;
        for (counter=0; counter<leerzeichen; counter++) {
            System.out.print(" ");
        }
        System.out.println(P);
        i = stelle;
        char t = T.charAt(i);
        char p = P.charAt(0);
        for (j=0; j<m; j++) {
            t = T.charAt(i+j);
            p = P.charAt(j);
            if (t != p) break; 
        }
        if (j == m) {
            System.out.println("match");
            System.out.println();
            return (stelle + 1);
        } else {
            if (stelle == T.length()-P.length()) return stelle+1;
            int muster_verschiebung = j-next2[j]; 
            for (counter=0; counter<j; counter++) {
                System.out.print(" ");
            }
            System.out.println("|");
            System.out.println("kein match @ Stelle:"+j+" Text:"+t+" Muster:"+p);
            System.out.println("verschieb zu Musterstelle next'("+j+") = "+next2[j]+" = "+muster_verschiebung+" Stellen");
            return (stelle + muster_verschiebung);
        }
    }

    public void matches_finden() {
        int stelle = 0;
        while (stelle < T.length()-P.length()) {
            stelle = this.vergleich_an_der_stelle(stelle);
        }
    }        

    public static void main(String[] args) {
        KnuthMorrisPratt kmp = new KnuthMorrisPratt(args[0],args[1]);
        kmp.mach_next_tabelle();    
        kmp.matches_finden();
    }
}

        


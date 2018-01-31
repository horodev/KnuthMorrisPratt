

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

    public void print_next_tabelle() {
        System.out.println("next Tabelle");
        System.out.println("------------");
        System.out.println("next wert = wieviele Stelle am Anfang des Musters sind genau wie das Submuster links von dieser Stelle");
        print_tabelle(this.P,this.next);
        System.out.println("next' Tabelle");
        System.out.println("-------------");
        System.out.println("setze next'[j] auf next[next[j]], wenn P[j] == P[next[j]]");
        System.out.println("wenn diese beide chars gleich sind (P[j],P[next[j]]), wir wissen dass die Muster da nicht passen wird, suchen wir die nachste match");
        print_tabelle(this.P,this.next2);
    }

    public void print_tabelle(String Muster, int[] werte) {
        int i;
        int menge = Muster.length();
        int menge_ = menge*4 - 1;
        System.out.print(" ");
        for (i = 0; i < menge_; i++) {
            System.out.print("_");
        }
        System.out.print("\n|");
        for (i = 0; i < menge; i++) {
            System.out.print(" "+Muster.charAt(i)+" |");
        }
        System.out.print("\n ");
        for (i = 0; i < menge_; i++) {
            System.out.print("_");
        }
        System.out.print("\n|");
        for (i = 0; i < menge; i++) {
            if (werte[i] >= 0) {
                System.out.print(" ");
            }
            System.out.print(werte[i]+" |");
        }
        System.out.print("\n ");
        for (i = 0; i < menge_; i++) {
            System.out.print("_");
        }
        System.out.println("\n");
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
        //System.out.println("setze next'[j] auf next[next[j]], wenn P[j] == P[next[j]]");
        //System.out.println("wenn diese beide chars gleich sind (P[j],P[next[j]]), wir wissen dass die Muster da nicht passen wird, suchen wir die nachste match");
        i = 0;
        j = -1;
        next2[0] = -1;
        while (i < m-1) {
            if ((j==-1) || (P.charAt(i) == P.charAt(j))) {
                i++;
                j++;
                if (P.charAt(i) == P.charAt(j)) {
                    next2[i] = next2[j];
                }
                else next2[i] = j;
            } else { 
                j = next2[j];
            }
        }
        print_next_tabelle();
    }

    public int vergleich_an_der_stelle(int stelle) {
        System.out.println(T);
        int counter;
        int leerzeichen = stelle;
        for (counter=0; counter<leerzeichen; counter++) {
            System.out.print(" ");
        }
        System.out.println(P); //print neben dem Text wo wir vergleichen werden
        i = stelle;
        char t = T.charAt(i);
        char p = P.charAt(0);
        for (j=0; j<m; j++) {
            t = T.charAt(i+j);
            p = P.charAt(j);
            if (t != p) break; //kein match  
        }
        if (j == m) {
            System.out.println("match");
            System.out.println();
            return (stelle + 1);
        } else {
            if (stelle == T.length()-P.length()) return stelle+1;
            int muster_verschiebung = j-next2[j]; 
            for (counter=0; counter<(i+j); counter++) {
                System.out.print(" ");
            }
            System.out.println("|");
            System.out.println("kein match @ Stelle:"+j+" Text:"+t+" Muster:"+p);
            System.out.println("verschieb zu Musterstelle next'("+j+") = "+next2[j]+" = "+muster_verschiebung+" Stellen \n");
            return (stelle + muster_verschiebung);
        }
    }

    public void matches_finden() {
        int stelle = 0;
        System.out.println("vergleichen und verschieben");
        System.out.println("---------------------------");
        while (stelle < T.length()-P.length()) {
            stelle = this.vergleich_an_der_stelle(stelle);
        }
    }        

    public static void main(String[] args) {
        KnuthMorrisPratt kmp = new KnuthMorrisPratt(args[0],args[1]);
        System.out.println("\n ********** KnuthMorrisPratt ********** \n");
        kmp.mach_next_tabelle();    
        kmp.matches_finden();
    }
}

        


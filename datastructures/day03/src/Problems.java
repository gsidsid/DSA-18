import java.util.*;

public class Problems {

    public static class Node {
        int val;
        Node next;

        Node(int d) {
            this.val = d;
            next = null;
        }
    }

    public static List<Integer> removeKDigits(int[] A, int k) {
        // TODO: your code here
        List<Integer> l = new LinkedList<>();
        for (int i = 0; i < A.length; i++) {
            l.add(A[i]);
        }
        for (int i = 0; i < l.size()-1; i++) {
                if(l.get(i) > l.get(i+1) && k > 0) {
                    l.remove(i);
                    k--;
                    i=0;
                }
        }
        while(k > 0) {
            l.remove(l.indexOf(Collections.max(l)));
            k--;
        }
        return l;
    }

    public static boolean isPalindrome(Node n) {
        // TODO: your code here

        Node lf = n;
        Node ls = n;
        Node midpoint = null;
        Node reverse_pointer = null;
        Node even_pointer = null;
        Node odd_pointer = null;

        if(n == null || n.next == null) {
            return true;
        }

            while(lf.next!=null && lf.next.next!=null){
                lf = lf.next.next;
                ls = ls.next;
            }

            midpoint = ls.next;
            ls.next = null;
            even_pointer = midpoint;
            odd_pointer = even_pointer.next;

            while(even_pointer!=null && odd_pointer!=null){
                Node temp = odd_pointer.next;
                odd_pointer.next = even_pointer;
                even_pointer = odd_pointer;
                odd_pointer = temp;
            }

            midpoint.next = null;

            if(odd_pointer == null) {
                reverse_pointer = even_pointer;
            } else {
                reverse_pointer = odd_pointer;
            }
            Node forward = n;
            while(reverse_pointer!=null){
                if(reverse_pointer.val != forward.val) {
                    return false;
                }
                reverse_pointer = reverse_pointer.next;
                forward = forward.next;
            }
        return true;
    }

    public static String infixToPostfix(String s) {
        // TODO

        String sh = "";
        Stack<Character> ops = new Stack<>();

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == ' ' || Character.isDigit(c)) {
                sh += c;
                sh = sh.trim();
            } else if ( c == '+' || c == '-' || c == '/' || c == '*') {
                ops.push(c);
            } else if (c == ')') {
                sh += ops.pop();
            }
        }

        return sh.replaceAll(".(?=.)", "$0 ");
    }



}

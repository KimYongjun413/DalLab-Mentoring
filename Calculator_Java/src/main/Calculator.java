import java.math.BigDecimal;
import java.security.PrivateKey;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.DoubleToLongFunction;

public class Calculator {
    public static void main(String[] args) {

        System.out.printf("\n계산식을 입력 하세요 : ");
        Scanner in = new Scanner(System.in);
        String formula = in.nextLine();

        BinaryTree binaryTree = new BinaryTree();
        binaryTree.showResult(binaryTree.binaryTreeNode(postFix(formula)));
    }

    private static class Node {

        private String data;
        protected Node left;
        protected Node right;

        public Node() {
            this.data = "";
            left = null;
            right = null;
        }

        public String getData() {
            return this.data;
        }
    }

    public static class BinaryTree {

        private Node root;

        public BinaryTree() {
            this.root = null;
        }

        public void setData(Node node, String data){
            node.data = data;
        }

        public Node leftSubTree(Node mainNode, Node leftNode){
            mainNode.left = leftNode;
            return mainNode;
        }

        public Node rightSubTree(Node mainNode, Node rightNode){
            mainNode.right = rightNode;
            return mainNode;
        }

        public String calculation(Node root){
            if(root != null){
                calculation(root.left);
                calculation(root.right);

                if(root.getData().equals("+")) {
                    root.data = add(root.right.data, root.left.data);
                } else if(root.getData().equals("-")) {
                    root.data = subtract(root.right.data, root.left.data);
                } else if(root.getData().equals("*")) {
                    root.data = multiply(root.right.data, root.left.data);
                } else if(root.getData().equals("/")) {
                    root.data = divide(root.right.data, root.left.data);
                }
                return root.getData();
            }
            return "";
        }

        public void showResult(Node root) {
            DecimalFormat fmt = new DecimalFormat("0.###############");
            System.out.println("답은 " + fmt.format(Double.parseDouble(calculation(root))) + "입니다.");
        }

        public Node binaryTreeNode(String pFormula){

            Stack<Node> stack = new Stack<>();
            String[] formula = pFormula.split(" ");
            Node newNode;

            try {
                for (int i = 0; i < formula.length; i++) {
                    newNode = new Node();
                    if (formula[i].equals("+") || formula[i].equals("-") || formula[i].equals("*") || formula[i].equals("/")) {
                        leftSubTree(newNode, stack.pop());
                        rightSubTree(newNode, stack.pop());
                        setData(newNode, formula[i]);
                    } else {
                        setData(newNode, formula[i]);
                    }
                    stack.push(newNode);
                }
            }catch (Exception e) {
                Node errNode = new Node();
                stack.clear();
                setData(errNode, "0");
                stack.push(errNode);
                throw e;
            }
            finally {
                return stack.pop();
            }

        }

    }

    static int getWeight(char opr) {

        switch (opr) {
            case '*':   case '/':
                return 3;
            case '+':   case '-':
                return 2;
            default:
                return 0;
        }
    }

    public static boolean isDouble( String input ) {
        try {
            Double.parseDouble(input);
            return true;
        }
        catch( NumberFormatException e ) {
            return false;
        }
    }

    static String postFix(String formula) {
        char[] charFormula = formula.toCharArray();

        return getPostFixResult(charFormula);
    }

    public static String getPostFixResult (char[] charFormula) {
        Stack<String> stack = new Stack<>();
        StringBuffer result = new StringBuffer();

        for (int i = 0; i < charFormula.length; i++) {
            switch (charFormula[i]) {
                case '0':   case '1':   case '2':   case '3':   case '4':
                case '5':   case '6':   case '7':   case '8':   case '9':
                case '.':
                    result = resultAppend(stack, charFormula, i, result);
                    break;
                case '+':   case '-':   case '/':   case '*': case '(':
                    result = pushByWeight(stack, charFormula, i, result);
                    break;
                case ')':
                    result = popByWeight(stack, charFormula, i, result);
                    break;
                case ' ': case ',':
                    break;
                default:
                    return "0";
            }
        }
        return popRemainAll(stack, result).toString();
    }

    public static StringBuffer popRemainAll(Stack<String> stack, StringBuffer result) {
        while (!stack.isEmpty()) {
            String peek = stack.peek();
            if (!peek.equals("(")) {
                if (peek.equals("+") || peek.equals("-") || peek.equals("*") || peek.equals("/")) {
                    result.append(" " + stack.pop());
                } else {
                    result.append(stack.pop());
                }
            }
            else stack.pop();
        }
        return result;
    }

    public static StringBuffer popByWeight(Stack<String> stack, char[] charFormula, int idx, StringBuffer result) {
        if(charFormula[idx] == ')') {
            while (!stack.peek().equals("(")) {
                result.append(" " + stack.pop());
            }
            stack.pop();
        }
        return result;
    }

    public static StringBuffer pushByWeight(Stack<String> stack, char[] charFormula, int idx, StringBuffer result) {
        if (stack.isEmpty() || charFormula[idx] == '(') {
            stack.push(String.valueOf(charFormula[idx]));
        } else if(charFormula[idx] == ')') {
            while (!stack.peek().equals("(")) result.append(" " + stack.pop());
            stack.pop();
        } else if(charFormula[idx - 1] == '+' || charFormula[idx - 1] == '=' || charFormula[idx - 1] == '*' || charFormula[idx - 1] == '/') {
            return new StringBuffer(0);
        } else {
            if (getWeight(stack.peek().charAt(0)) >= getWeight(charFormula[idx])) {
                result.append(" " + stack.pop());
                stack.push(String.valueOf(charFormula[idx]));
            } else {
                stack.push(String.valueOf(charFormula[idx]));
            }
        }
        return result;
    }

    public static StringBuffer resultAppend(Stack<String> stack, char[] charFormula, int idx, StringBuffer result) {
        char values = charFormula[idx];
        String preValues = "";
        if(idx != 0) preValues = String.valueOf(charFormula[idx - 1]);

        if(stack.empty() || (stack.peek().equals("(") && stack.size() == 1)) result.append(values);
        else if(idx != 0 && preValues.equals(".") && isDouble(String.valueOf(values))) {
            result.append(values);
        }
        else if(idx != 0 && isDouble(preValues)) result.append(values);
        else result.append(" " + values);

        return result;
    }

    static String add(String a, String b) {
        return String.valueOf(Double.parseDouble(a) + Double.parseDouble(b));
    }

    static String subtract(String a, String b) {
        BigDecimal bigDecimalA = new BigDecimal(a);
        BigDecimal bigDecimalB = new BigDecimal(b);
        return String.valueOf(bigDecimalA.subtract(bigDecimalB));
    }

    static String multiply(String a, String b) {
        return String.valueOf(Double.parseDouble(a) * Double.parseDouble(b));
    }

    static String divide(String a, String b) {
        double valueA = Double.parseDouble(a);
        double valueB = Double.parseDouble(b);
        if(valueA < 0 && valueB == 0) return "-infinity";
        else if(valueA > 0 && valueB == 0)  return "infinity";
        else if( valueA == 0 && valueB == 0) return "0";

        return String.valueOf(valueA / valueB);
    }
}

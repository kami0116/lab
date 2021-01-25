package per.gh.study.datastructure.stack;

import org.junit.Before;
import org.junit.Test;

/**
 * 使用stack完成算式分析
 */
public class StackTest {
    Stack<Byte> ops;
    Stack<Integer> nums;
    String expression;

    @Before
    public void prepare() {
        ops = new ArrayStack<>();
        nums = new ArrayStack<>();
        expression = "12 + 6 * 15 / 9 - 3";
    }

    @Test
    public void test() {
        byte[] bytes = expression.getBytes();
        boolean lastIsNum = false;
        for (byte b : bytes) {
            if (b == '+' || b == '-' || b == '*' || b == '/') {
                lastIsNum = false;
                while (!ops.isEmpty() && (ops.peek()=='*'||ops.peek()=='/'||b=='+'||b=='-') ){
                    nums.push(calculate(ops.pop(),nums.pop(),nums.pop()));
                }
                ops.push(b);
            } else if (b >= '0' && b <= '9') {
                if (lastIsNum) {
                    nums.push(nums.pop() * 10 + (b - '0'));
                } else {
                    nums.push(b-'0');
                }
                lastIsNum = true;
            }
        }

        while(!ops.isEmpty()){
            nums.push(calculate(ops.pop(),nums.pop(),nums.pop()));
        }
        System.out.println(nums.pop());
        assert nums.isEmpty();
        assert ops.isEmpty();
    }
    private int calculate(byte op, int n2,int n1){
        System.out.println((char) op);
        switch (op){
            case '+':
                return n1+n2;
            case '-':
                return n1-n2;
            case '*':
                return n1*n2;
            case '/':
                return n1/n2;
            default:
                throw new IllegalArgumentException(String.valueOf((char) op));
        }
    }
}

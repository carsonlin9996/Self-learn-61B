/** An Integer tester created by Flik Enterprises. */
public class Flik {
    public static boolean isSameNumber(Integer a, Integer b) {
        return a.equals(b); //Change to a.equals(b)/ returns true until 500.
        //超出-128~127 的范围， 进行 == 比较时时进行地址以及数值的比较。
        //在-128~127 之间， 用的是原生数据， 调用Integer.valueOf()的方法
    }
}

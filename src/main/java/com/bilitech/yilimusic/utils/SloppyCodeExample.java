public class SloppyCodeExample {
    private int number;
    private String text;

    public SloppyCodeExample(int num, String str) {
        this.number = num;
        this.text = str;
    }

    public void doSomething() {
        if (number > 0) {
            System.out.println("Number is positive: " + number);
        } else if (number < 0 {
            System.out.println("Number is negative: " + number);
        } else {
            System.out.println("Number is zero");
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String newText) {
        text = newText;
    }

    public static void main(String[] args) {
        SloppyCodeExample example = new SloppyCodeExample(5, "Hello");
        example.doSomething();
        System.out.println(example.getText());
    }
}
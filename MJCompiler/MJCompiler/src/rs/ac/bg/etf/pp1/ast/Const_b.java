// generated with ast extension for cup
// version 0.8
// 26/0/2022 16:37:4


package rs.ac.bg.etf.pp1.ast;

public class Const_b extends Const {

    private Integer B1;

    public Const_b (Integer B1) {
        this.B1=B1;
    }

    public Integer getB1() {
        return B1;
    }

    public void setB1(Integer B1) {
        this.B1=B1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Const_b(\n");

        buffer.append(" "+tab+B1);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Const_b]");
        return buffer.toString();
    }
}

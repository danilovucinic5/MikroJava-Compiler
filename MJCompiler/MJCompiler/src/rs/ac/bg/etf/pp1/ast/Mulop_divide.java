// generated with ast extension for cup
// version 0.8
// 26/0/2022 16:37:4


package rs.ac.bg.etf.pp1.ast;

public class Mulop_divide extends Mulop {

    public Mulop_divide () {
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
        buffer.append("Mulop_divide(\n");

        buffer.append(tab);
        buffer.append(") [Mulop_divide]");
        return buffer.toString();
    }
}

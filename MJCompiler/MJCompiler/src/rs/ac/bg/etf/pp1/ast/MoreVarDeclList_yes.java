// generated with ast extension for cup
// version 0.8
// 26/0/2022 16:37:4


package rs.ac.bg.etf.pp1.ast;

public class MoreVarDeclList_yes extends MoreVarDeclList {

    private VarDeclList VarDeclList;
    private MoreVarDeclList MoreVarDeclList;

    public MoreVarDeclList_yes (VarDeclList VarDeclList, MoreVarDeclList MoreVarDeclList) {
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
        this.MoreVarDeclList=MoreVarDeclList;
        if(MoreVarDeclList!=null) MoreVarDeclList.setParent(this);
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public MoreVarDeclList getMoreVarDeclList() {
        return MoreVarDeclList;
    }

    public void setMoreVarDeclList(MoreVarDeclList MoreVarDeclList) {
        this.MoreVarDeclList=MoreVarDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclList!=null) VarDeclList.accept(visitor);
        if(MoreVarDeclList!=null) MoreVarDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
        if(MoreVarDeclList!=null) MoreVarDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        if(MoreVarDeclList!=null) MoreVarDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MoreVarDeclList_yes(\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MoreVarDeclList!=null)
            buffer.append(MoreVarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MoreVarDeclList_yes]");
        return buffer.toString();
    }
}

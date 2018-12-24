package  com.example.lib.clone;

/**
 * Created by guozhk on 2018/5/29.
 */

public class Address implements Cloneable {

    private String add;


    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }


    @Override
    protected Object clone()  {
        Address addr = null;
        try {
            addr = (Address) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return addr;
    }
}

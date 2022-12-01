package Pojos;

public class GoRestPojo {
   /* {
        "meta": null,
            "data": {
        "id": 13,
                "name": "Rahul Jha",
                "email": "jha_rahul@beahan.co",
                "gender": "male",
                "status": "active"
    }
    }*/

    private Object meta;
    private GoRestDataPojo data;

    public GoRestPojo(Object meta, GoRestDataPojo data) {
        this.meta = meta;
        this.data = data;
    }

    public GoRestPojo() {
    }

    public Object getMeta() {
        return meta;
    }

    public void setMeta(Object meta) {
        this.meta = meta;
    }

    public GoRestDataPojo getData() {
        return data;
    }

    public void setData(GoRestDataPojo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GoRestPojo{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }
}

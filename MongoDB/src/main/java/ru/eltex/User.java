package ru.eltex;


class User implements CSV{
    protected Integer id;
    protected String fio;
    protected String phone;
    protected String email;

    User() {}

    User(Integer id, String fio, String phone, String email) {
        this.id = id;
        this.fio = fio;
        this.phone = phone;
        this.email = email;
    }

    public void printInf() {

        System.out.println("User id: " + this.id);
        System.out.println("User fio: " + this.fio);
        System.out.println("User phone: " + this.phone);
        System.out.println("User email: " + this.email);

        System.out.println();
        System.out.println();
    }

    public Integer getId () {

        return this.id;
    }

    public String getFio () {

        return this.fio;
    }

    public String getPhone () {

        return this.phone;
    }

    public String getEmail () {

        return this.email;
    }

    public void setId (Integer arg) {

        this.id = arg;
    }


    public void setFio (String arg) {

        this.fio = arg;
    }

    public void setPhone (String arg) {

        this.phone = arg;
    }

    public void setEmail (String arg) {

        this.email = arg;
    }

    public String toCSV() {

        return Integer.toString(this.id) + ";" + this.fio + ";" + this.phone + ";" + this.email + ";";
    }

    public Integer fromCSV(String str) {
        String [] arg = str.split(";");
        if(arg.length == 4) {
            setId (Integer.valueOf(arg [0]));
            setFio (arg [1]);
            setPhone (arg [2]);
            setEmail (arg [3]);
        }else
            return -1;

        return 0;
    }
}

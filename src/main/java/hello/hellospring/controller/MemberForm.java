package hello.hellospring.controller;

public class MemberForm {
    private String name;          // members 폴더의 createMemberForm.html의 "name"으로 인자 들어감

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

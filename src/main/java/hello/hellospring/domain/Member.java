package hello.hellospring.domain;

public class Member {

    private Long id;               // 고객이 정하는 값이 아닌 데이터 구분하기 위해 시스템이 임의로 정하는 값
    private String name;           // 사용자 이름

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

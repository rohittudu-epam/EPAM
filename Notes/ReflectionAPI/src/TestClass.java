public class TestClass {
    final String testUser;
    final String testUserId;
    final String testUserType;

    TestClass(String user, String userId, String userType){
        this.testUser = user;
        this.testUserId = userId;
        this.testUserType = userType;
    }

    public String getTestUserId() {
        return testUserId;
    }

    public String getTestUser() {
        return testUser;
    }

    public String getTestUserType() {
        return testUserType;
    }

}

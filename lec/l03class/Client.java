public class Client {
    private final String name;
    private final String passport;

    public Client(String name, String passport) {
        this.name = name;
        // this.passport = Objects.requireNonNull(passport);
        this.passport = null;
    }

    public String getName() {
        return name;
    }

    public boolean checkPassport(String passport) {
        if (this.passport == null) {
            return passport == null;
        }
        return this.passport.equals(passport);
    }
}

package domains;

/**
 * Written by Luca Weiss (z3ntu)
 * https://github.com/z3ntu
 */
public abstract class User <DOMAIN_TYPE extends  User, PK_TYPE extends String> implements Comparable<DOMAIN_TYPE> {
    private PK_TYPE id;

    private Integer version;
}

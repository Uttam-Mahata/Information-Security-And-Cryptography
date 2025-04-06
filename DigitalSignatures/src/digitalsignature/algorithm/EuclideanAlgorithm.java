package digitalsignature.algorithm;

public class EuclideanAlgorithm {
    
    /**
     * Computes the greatest common divisor of two integers using Euclidean algorithm
     * @param a first integer
     * @param b second integer
     * @return gcd(a,b)
     */
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
    
    /**
     * Extended Euclidean Algorithm to find Bézout coefficients
     * @param a first integer
     * @param b second integer
     * @return array [gcd, x, y] where ax + by = gcd(a,b)
     */
    public static int[] extendedGcd(int a, int b) {
        if (b == 0) {
            return new int[] {a, 1, 0};
        }
        int[] values = extendedGcd(b, a % b);
        int gcd = values[0];
        int x1 = values[1];
        int y1 = values[2];
        int x = y1;
        int y = x1 - (a / b) * y1;
        return new int[] {gcd, x, y};
    }
    
    /**
     * Computes the modular multiplicative inverse using Extended Euclidean Algorithm
     * @param a integer to find inverse of
     * @param m modulus
     * @return a^(-1) mod m
     */
    public static int modInverse(int a, int m) {
        int[] values = extendedGcd(a, m);
        if (values[0] != 1) {
            throw new ArithmeticException("Modular inverse doesn't exist for " + a + " and " + m);
        }
        // Ensure result is positive
        return (values[1] % m + m) % m;
    }
    
    /**
     * Performs modular exponentiation (base^exponent mod modulus)
     * @param base the base
     * @param exponent the exponent
     * @param modulus the modulus
     * @return base^exponent mod modulus
     */
    public static int modPow(int base, int exponent, int modulus) {
        if (modulus == 1) return 0;
        int result = 1;
        base = base % modulus;
        while (exponent > 0) {
            if (exponent % 2 == 1) {
                result = (result * base) % modulus;
            }
            exponent = exponent >> 1;
            base = (base * base) % modulus;
        }
        return result;
    }
}

package digitalsignature.algorithm;

public class DSA {
    /*
     * Digital Signature Algorithm (DSA) is a Federal Information Processing Standard for digital signatures.
     * It is specified in FIPS PUB 186-4.
     * It is based on the mathematical concept of modular exponentiation and the discrete logarithm problem.
     * DSA is used to generate a digital signature for a message, which can be used to verify the authenticity and integrity of the message.
     * The DSA algorithm consists of three steps: key generation, signing, and signature verification.
     * Key generation involves generating a pair of keys: a private key and a public key.
     * The private key is kept secret, while the public key is shared with others.
     * Signing involves using the private key to create a digital signature for a message.
     * Signature verification involves using the public key to verify the digital signature.
     * The DSA algorithm is widely used in various applications, including secure email, digital certificates, and electronic transactions.
     * It is important to note that DSA is not suitable for encrypting messages, as it is only used for signing and verifying signatures.
     * It is also important to use a secure random number generator to generate the keys and signatures, as the security of DSA relies on the randomness of the keys and signatures.
     * In summary, DSA is a widely used digital signature algorithm that provides a secure way to sign and verify messages.
     */

    // DSA parameters
    private static final int P = 283; // prime number
    private static final int Q = 47; // prime divisor of P-1
    private static final int G = 60; // generator
    private static final int X = 24; // private key
    private static final int Y = EuclideanAlgorithm.modPow(G, X, P); // public key
    private static final int K = 15; // random number
    private static final int H = 41; // hash of the message

    // Inverse of K mod Q using EuclideanAlgorithm
    private static final int K_INV = EuclideanAlgorithm.modInverse(K, Q); // inverse of K mod Q

    public boolean verifySignature(int r, int s, int H) {
        // Verify the signature using the public key
        // Calculate W, U1, U2
        int W = EuclideanAlgorithm.modInverse(s, Q); // s^-1 mod Q
        System.out.println("W: " + W);
        int U1 = (H * W) % Q;
        System.out.println("U1: " + U1);
        int U2 = (r * W) % Q;
        System.out.println("U2: " + U2);
        
        // Calculate V1 and V2 using proper modular exponentiation
        int V1_part1 = EuclideanAlgorithm.modPow(G, U1, P);
        int V1_part2 = EuclideanAlgorithm.modPow(Y, U2, P);
        int V1 = ((V1_part1 * V1_part2) % P) % Q;
        System.out.println("V1: " + V1);
        
        int V2 = r;
        System.out.println("V2: " + V2);
        
        if (V1 != V2) {
            return false; // Signature is invalid
        }
        
        return true;
    }
    
    public int[] signMessage(int H) {
        // Use proper modular exponentiation for R
        int R = (EuclideanAlgorithm.modPow(G, K, P)) % Q;
        
        // Calculate S correctly
        int S = (K_INV * ((H + X * R) % Q)) % Q;
        
        System.out.println("K_INV: " + K_INV);
        System.out.println("H + X*R mod Q: " + ((H + X * R) % Q));
        System.out.println("R: " + R + ", S: " + S);
        
        return new int[]{R, S};
    }

    public static void main(String[] args) {
        DSA dsa = new DSA();
        int[] signature = dsa.signMessage(H);
        System.out.println("Signature: " + signature[0] + ", " + signature[1]);
        boolean isValid = dsa.verifySignature(signature[0], signature[1], H);
        System.out.println("Signature valid: " + isValid);
        System.out.println("R: " + signature[0] + ", S: " + signature[1]);
    }
}
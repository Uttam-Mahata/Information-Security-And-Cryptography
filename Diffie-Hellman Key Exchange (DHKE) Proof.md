The **Diffie-Hellman Key Exchange (DHKE)** algorithm allows two parties to securely exchange a shared secret over an insecure channel. The security of the algorithm relies on the hardness of the **Discrete Logarithm Problem (DLP)**. Below is a mathematical proof of its correctness.

---

### **Diffie-Hellman Key Exchange Setup**
1. **Public Parameters**:
   - A large prime number \( p \).
   - A generator \( g \) of the multiplicative group \( \mathbb{Z}_p^* \) (i.e., \( g \) is a primitive root modulo \( p \)).

2. **Private and Public Keys**:
   - Party \( A \) chooses a private key \( a \) (a random integer \( 1 \leq a \leq p-1 \)) and computes their public key \( A = g^a \mod p \).
   - Party \( B \) chooses a private key \( b \) (a random integer \( 1 \leq b \leq p-1 \)) and computes their public key \( B = g^b \mod p \).

3. **Shared Secret**:
   - Party \( A \) computes the shared secret \( S = B^a \mod p \).
   - Party \( B \) computes the shared secret \( S = A^b \mod p \).

---

### **Proof of Correctness**
We need to show that both parties compute the same shared secret \( S \).

1. From Party \( A \)'s perspective:
   \[
   S = B^a \mod p
   \]
   Substitute \( B = g^b \mod p \):
   \[
   S = (g^b)^a \mod p
   \]
   Simplify using exponentiation rules:
   \[
   S = g^{ba} \mod p
   \]

2. From Party \( B \)'s perspective:
   \[
   S = A^b \mod p
   \]
   Substitute \( A = g^a \mod p \):
   \[
   S = (g^a)^b \mod p
   \]
   Simplify using exponentiation rules:
   \[
   S = g^{ab} \mod p
   \]

3. Since \( g^{ba} = g^{ab} \) (because multiplication is commutative in the exponent), both parties compute the same shared secret:
   \[
   S = g^{ab} \mod p
   \]

---

### **Security of the Shared Secret**
The shared secret \( S = g^{ab} \mod p \) is secure because:
1. An eavesdropper who observes \( A = g^a \mod p \) and \( B = g^b \mod p \) cannot efficiently compute \( S = g^{ab} \mod p \) without knowing either \( a \) or \( b \). This is the **Discrete Logarithm Problem (DLP)**, which is computationally hard for large \( p \).
2. The security of the protocol relies on the difficulty of solving the DLP.

---

### **Conclusion**
The Diffie-Hellman Key Exchange algorithm is correct because both parties compute the same shared secret \( S = g^{ab} \mod p \). The security of the protocol is based on the hardness of the Discrete Logarithm Problem.

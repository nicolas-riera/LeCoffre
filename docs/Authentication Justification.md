# Authentication Choice: Stateful Sessions over JWT

For this team secrets management API built with **Spring Boot**, we chose **server-side stateful sessions** (using secure `HttpOnly` cookies) rather than stateless **JSON Web Tokens (JWT)**.

## 1. Immediate Session Revocation
In a secrets management application, security incidents require an instantaneous response. If an account is compromised or a team member leaves, their access must be cut off immediately. 
* **With Sessions:** Invalidating a session on the server (`session.invalidate()` or deleting the session record from the database/Redis) blocks any further API requests at the exact millisecond of revocation.
* **With JWT:** A stateless token remains valid until its expiration timestamp (`exp`). Achieving immediate revocation with JWT requires maintaining a server-side token blacklist, which cancels out the primary stateless advantage of JWTs.

## 2. Reduced Attack Surface (XSS Protection)
Transporting the session identifier via a cookie configured with `HttpOnly`, `Secure`, and `SameSite=Strict` flags protects the credential from client-side scripts:
* Because the cookie is `HttpOnly`, JavaScript running in the browser cannot read it, preventing session hijacking via Cross-Site Scripting (XSS) vulnerabilities.
* In contrast, JWTs are frequently stored in `localStorage` or `sessionStorage`, making them directly accessible and vulnerable if an XSS flaw occurs anywhere in the frontend.

## 3. Native Spring Security Integration & Simplicity
Spring Security is designed around stateful session management out of the box (`SessionCreationPolicy.IF_REQUIRED`).
* Using native sessions avoids writing custom authentication filters (`OncePerRequestFilter`), managing cryptographic signing keys, or implementing complex refresh-token rotation flows.
* Keeping the authentication mechanism standard and minimal reduces custom security boilerplate and lowers the risk of implementation bugs.
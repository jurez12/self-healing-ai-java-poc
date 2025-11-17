# Self-Healing AI Java POC (Selenium + TestNG)

**Contents**
- Java Maven project that demonstrates AI-assisted self-healing locators (AISuggester is a Java heuristic 'AI').
- Three demo tests: Search flow, Home page checks, Login page checks.

## Quick start
1. Ensure Java 17+ and Maven are installed.
2. Put a compatible `chromedriver` binary in `./drivers/chromedriver` (or update `DriverFactory` path).
3. From the project root run:
   ```bash
   mvn test
   ```
4. Tests will run (they open amazon.com). If a primary locator fails the AISuggester will attempt alternatives and persist healed locators to `healed-locators.json`.

## How to demo healing
- Edit `LocatorRepository` and change a primary locator (e.g., change id value to a wrong one).
- Re-run `mvn test` and observe console for `[AI-HEAL]` messages and the `healed-locators.json` file.

## Notes
- This is a POC. The AISuggester is heuristic-based and intentionally simple so it runs without external APIs.
- For production you can replace AISuggester with an LLM-backed service or add visual-embedding fallbacks.

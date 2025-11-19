# Self-Healing AI Java POC (Selenium + TestNG)

**Contents**
- Java Maven project that demonstrates AI-assisted self-healing locators (LLM(Google Gemini 2.5  is a Java heuristic 'AI').
- Validate ITC page and its inner elements

## Quick start
1. Ensure Java 17+ and Maven are installed.
2. Put a compatible `chromedriver` binary in `./drivers/chromedriver` (or update `DriverFactory` path).
3. From the project root run:
   ```bash
   mvn test
   ```
4. Tests will run (they open itcinfotetch.com). If a primary locator fails the LLM(Google Gemini 2.5) will attempt alternatives and persist healed locators to `healed-locators.json`.

## How to demo healing
- Edit `LocatorRepository` and change a primary locator (e.g., change id value to a wrong one).
- Re-run `mvn test` and observe console for `[AI-HEAL]` messages and the `healed-locators.json` file.

## Notes
- This is a POC. The LLM(Google Gemini 2.5) is heuristic-based and intentionally simple so it runs without external APIs.


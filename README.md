<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>

<h1>Self-Healing AI Java POC (Selenium + TestNG)</h1>

<h2>Contents</h2>
<ul>
    <li>Java Maven project that demonstrates AI-assisted self-healing locators (LLM(Google Gemini 2.5 is a Java heuristic 'AI').</li>
    <li>Validate ITC page and its inner elements</li>
</ul>

<h2>Quick start</h2>
<ol>
    <li>Ensure Java 17+ and Maven are installed.</li>
    <li>Put a compatible
        <pre><code>chromedriver</code></pre>
        binary in
        <pre><code>./drivers/chromedriver</code></pre>
        (or update
        <pre><code>DriverFactory</code></pre>
        path).
    </li>
    <li>From the project root run:
        <pre><code>mvn test</code></pre>
    </li>
    <li>Tests will run (they open itcinfotetch.com). If a primary locator fails the LLM(Google Gemini 2.5) will attempt alternatives and persist healed locators to
        <pre><code>healed-locators.json</code></pre>
    </li>
</ol>

<h2>How to demo healing</h2>
<ul>
    <li>Edit
        <pre><code>LocatorRepository</code></pre>
        and change a primary locator (e.g., change id value to a wrong one).
    </li>
    <li>Re-run
        <pre><code>mvn test</code></pre>
        and observe console for
        <pre><code>[AI-HEAL]</code></pre>
        messages and the
        <pre><code>healed-locators.json</code></pre>
        file.
    </li>
</ul>

<h2>Notes</h2>
<ul>
    <li>This is a POC. The LLM(Google Gemini 2.5) is heuristic-based and intentionally simple so it runs without external APIs.</li>
</ul>

<h2>About</h2>
<p>self-Healing</p>


<h2>Contributors</h2>
<ul>
    <li>Suresh Babu G</li>
    <li>Anant Parmar</li>
    <li>Theja Nanga</li>
</ul>

<h2>Languages</h2>
<p>Java 100.0%</p>

<h2><B>**Release : Self-Healing Heuristic  AI 1.0** </B></h2>
</body>
</html>

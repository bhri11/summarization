import { useEffect, useState } from "react";

export default function App() {
  const [text, setText] = useState("");
  const [summary, setSummary] = useState("");
  const [history, setHistory] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  async function loadHistory() {
    setError("");
    const res = await fetch("/api/summaries");
    if (!res.ok) throw new Error("Failed to load history");
    const data = await res.json();
    setHistory(data);
  }

  useEffect(() => {
    loadHistory().catch(() => setError("History could not be loaded."));
  }, []);

  async function handleSummarize() {
    setError("");
    setLoading(true);
    try {
      const res = await fetch("/api/summarize", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ text }),
      });

      if (!res.ok) throw new Error("Summarize request failed");

      const data = await res.json();
      setSummary(data.summary || "");
      await loadHistory();
    } catch (e) {
      setError("Summarize failed.");
    } finally {
      setLoading(false);
    }
  }

  return (
    <div style={{ maxWidth: 900, margin: "40px auto", padding: 16, fontFamily: "Arial" }}>
      <h2>Summarization App (Demo)</h2>

      {error && (
        <div style={{ background: "#ffe5e5", padding: 10, marginBottom: 12 }}>
          {error}
        </div>
      )}

      <div style={{ display: "grid", gap: 12 }}>
        <textarea
          value={text}
          onChange={(e) => setText(e.target.value)}
          rows={6}
          placeholder="Paste text here..."
          style={{ width: "100%", padding: 10 }}
        />

        <button
          onClick={handleSummarize}
          disabled={loading || text.trim().length === 0}
          style={{ padding: "10px 14px", cursor: "pointer" }}
        >
          {loading ? "Summarizing..." : "Summarize"}
        </button>

        <textarea
          value={summary}
          readOnly
          rows={4}
          placeholder="Summary will appear here..."
          style={{ width: "100%", padding: 10 }}
        />
      </div>

      <hr style={{ margin: "24px 0" }} />

      <h3>History (Demo User)</h3>
      <div style={{ display: "grid", gap: 10 }}>
        {history.map((item) => (
          <div key={item.id} style={{ border: "1px solid #ddd", padding: 12 }}>
            <div style={{ fontSize: 12, opacity: 0.7 }}>
              #{item.id} — {item.createdAt}
            </div>
            <div style={{ marginTop: 8 }}>
              <b>Original:</b>
              <div>{item.originalText}</div>
            </div>
            <div style={{ marginTop: 8 }}>
              <b>Summary:</b>
              <div>{item.summaryText}</div>
            </div>
          </div>
        ))}

        {history.length === 0 && <div>No summaries yet.</div>}
      </div>
    </div>
  );
}

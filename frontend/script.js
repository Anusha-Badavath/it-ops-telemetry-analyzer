// ===============================
// System Health
// ===============================

async function loadSystemHealth() {

    try {

        const response = await fetch(
            "https://telemetry-backend-57ll.onrender.com/api/analytics/health"
        );

        const health = await response.text();

        const healthElement =
            document.getElementById("system-health");

        healthElement.textContent = health;

        healthElement.className = "";

        if (health === "CRITICAL") {

            healthElement.classList.add("health-critical");

        } else if (health === "WARNING") {

            healthElement.classList.add("health-warning");

        } else {

            healthElement.classList.add("health-healthy");

        }

    } catch (error) {

        console.error("Error loading system health:", error);

        document.getElementById("system-health").textContent =
            "Unable to load health";
    }
}


// ===============================
// Severity Counts
// ===============================

async function loadSeverityCounts() {

    try {

        const response = await fetch(
            "https://telemetry-backend-57ll.onrender.com/api/analytics/severity"
        );

        const data = await response.json();

        document.getElementById("info-count").textContent =
            data.INFO || 0;

        document.getElementById("warn-count").textContent =
            data.WARN || 0;

        document.getElementById("critical-count").textContent =
            data.CRITICAL || 0;

    } catch (error) {

        console.error("Error loading severity counts:", error);

    }
}


// ===============================
// Component Statistics
// ===============================

async function loadComponentStats() {

    try {

        const response = await fetch(
            "https://telemetry-backend-57ll.onrender.com/api/analytics/components"
        );

        const data = await response.json();

        const container =
            document.getElementById("component-stats");

        container.innerHTML = "";

        for (const component in data) {

            const item = document.createElement("div");

            item.className = "component-item";

            const name = document.createElement("span");

            name.textContent = component;

            const count = document.createElement("strong");

            count.textContent = data[component];

            item.appendChild(name);

            item.appendChild(count);

            container.appendChild(item);
        }

    } catch (error) {

        console.error(
            "Error loading component statistics:",
            error
        );

    }
}


// ===============================
// Critical Issues by Component
// ===============================

async function loadCriticalStats() {

    try {

        const response = await fetch(
            "https://telemetry-backend-57ll.onrender.com/api/analytics/critical"
        );

        const data = await response.json();

        const container =
            document.getElementById("critical-stats");

        container.innerHTML = "";

        for (const component in data) {

            const item = document.createElement("div");

            item.className = "critical-item";

            const name = document.createElement("span");

            name.textContent = component;

            const count = document.createElement("strong");

            count.textContent = data[component];

            item.appendChild(name);

            item.appendChild(count);

            container.appendChild(item);
        }

    } catch (error) {

        console.error(
            "Error loading critical statistics:",
            error
        );

    }
}


// ===============================
// Telemetry Logs
// ===============================

async function loadLogs() {

    try {

        const response = await fetch(
            "https://telemetry-backend-57ll.onrender.com/api/logs"
        );

        const logs = await response.json();

        const tableBody =
            document.getElementById("logs-table-body");

        tableBody.innerHTML = "";

        logs.forEach(log => {

            const row = document.createElement("tr");

            row.innerHTML = `
                <td>${log.timestamp}</td>
                <td>${log.severity}</td>
                <td>${log.component}</td>
                <td>${log.message}</td>
            `;

            tableBody.appendChild(row);
        });

    } catch (error) {

        console.error("Error loading logs:", error);

        document.getElementById("logs-table-body").innerHTML = `
            <tr>
                <td colspan="4">
                    Unable to load logs
                </td>
            </tr>
        `;
    }
}


// ===============================
// Load Dashboard Data
// ===============================

loadSystemHealth();

loadSeverityCounts();

loadComponentStats();

loadCriticalStats();

loadLogs();
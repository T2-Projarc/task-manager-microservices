import { getToken, setToken, removeToken } from "./authUtils.js";

const taskServiceUrl = "http://localhost:8082/tasks";
const notificationServiceUrl = "http://localhost:8081/notifications";
const authServiceUrl = "http://localhost:8080/auth";


// Função para registrar um novo usuário
window.register = async function register() {
    const registerData = {
        username: document.getElementById("registerUsername").value,
        password: document.getElementById("registerPassword").value,
    };

    console.log("Dados de registro:", registerData);

    try {
        const response = await fetch(`${authServiceUrl}/register`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(registerData),
        });

        console.log("Resposta do backend:", response);

        if (response.ok) {
            showMessage("Registro bem-sucedido! Por favor, faça login.", "success");
            toggleSections();
        } else {
            const errorText = await response.text();
            showMessage("Falha no registro: " + errorText, "error");
        }
    } catch (error) {
        console.error("Erro durante o registro:", error);
        showMessage("Erro durante o registro.", "error");
    }
};

// Função para login
window.login = async function login() {
    const loginData = {
        username: document.getElementById("loginUsername").value,
        password: document.getElementById("loginPassword").value,
    };

    try {
        const response = await fetch(`${authServiceUrl}/login`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(loginData),
        });

        if (response.ok) {
            const data = await response.json();
            setToken(data.token);
            localStorage.setItem("username", loginData.username);

            showLoggedInSection();
            getAllTasks();
            getNotifications();
        } else {
            const errorText = await response.text();
            showMessage("Falha no login: " + errorText, "error");
        }
    } catch (error) {
        console.error("Erro durante o login:", error);
        showMessage("Erro durante o login.", "error");
    }
}

// Função para logout
window.logout = function logout() {
    removeToken();
    localStorage.removeItem("username");
    showLoggedOutSection();
}

// Função para alternar entre seções de registro e login
window.toggleSections = function toggleSections() {
    const registrationSection = document.getElementById("registrationSection");
    const loginSection = document.getElementById("loginSection");
    registrationSection.classList.toggle("hidden");
    loginSection.classList.toggle("hidden");
}

// Função para mostrar a interface de usuário logado
function showLoggedInSection() {
    document.getElementById("registrationSection").classList.add("hidden");
    document.getElementById("loginSection").classList.add("hidden");
    document.getElementById("loggedInSection").classList.remove("hidden");
}

// Função para mostrar a interface de usuário deslogado
function showLoggedOutSection() {
    document.getElementById("registrationSection").classList.remove("hidden");
    document.getElementById("loginSection").classList.add("hidden");
    document.getElementById("loggedInSection").classList.add("hidden");
}

// Função para criar uma nova tarefa
window.createTask = async function createTask() {
    const description = document.getElementById("description").value;
    let notificationTime = document.getElementById("notificationTime").value;
    const priority = document.getElementById("priority").value;
    const status = document.getElementById("status").value;

    if (notificationTime.length === 16) {
        notificationTime += ":00";
    }

    const taskData = {
        description,
        notificationTime,
        priority,
        status
    };

    try {
        const response = await fetch(`${taskServiceUrl}/create`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + getToken()
            },
            body: JSON.stringify(taskData)
        });

        if (response.ok) {
            showMessage("Tarefa criada com sucesso!", "success");
            document.getElementById("description").value = "";
            document.getElementById("notificationTime").value = "";
            document.getElementById("priority").value = "MEDIUM";
            document.getElementById("status").value = "PENDING";
            getAllTasks();
        } else {
            const errorText = await response.text();
            showMessage("Erro ao criar tarefa: " + errorText, "error");
        }
    } catch (error) {
        console.error("Erro ao criar tarefa:", error);
        showMessage("Ocorreu um erro ao criar a tarefa.", "error");
    }
}


// Função para obter todas as tarefas do usuário autenticado
window.getAllTasks = async function getAllTasks() {
    try {
        const response = await fetch(`${taskServiceUrl}/all`, {
            headers: {
                "Authorization": "Bearer " + getToken()
            }
        });
        if (response.ok) {
            const tasks = await response.json();
            const tasksList = document.getElementById("tasksList");
            tasksList.innerHTML = "";

            if (tasks.length === 0) {
                tasksList.innerHTML = "<p>Você ainda não tem tarefas.</p>";
            } else {
                tasks.forEach(task => {
                    const taskItem = document.createElement("div");
                    taskItem.classList.add("task-item");
                    taskItem.innerHTML = `
            <strong>Descrição:</strong> ${task.description || "N/A"} <br>
            <strong>Prioridade:</strong> ${task.priority || "N/A"} <br>
            <strong>Status:</strong> ${task.status || "N/A"} <br>
            <strong>Hora da Notificação:</strong> ${task.notificationTime.replace("T", " ")} <br>
            <strong>Notificado:</strong> ${task.notified ? "Sim" : "Não"}
            <br>
            <button onclick="editField(${task.id}, 'description')">Editar Descrição</button>
            <button onclick="editField(${task.id}, 'priority')">Editar Prioridade</button>
            <button onclick="editField(${task.id}, 'status')">Editar Status</button>
            <button onclick="deleteTask(${task.id})">Excluir</button>
        `;
                    tasksList.appendChild(taskItem);
                });
            }
        } else {
            const errorText = await response.text();
            showMessage("Falha ao obter tarefas: " + errorText, "error");
        }
    } catch (error) {
        console.error("Erro ao obter tarefas:", error);
        showMessage("Ocorreu um erro ao obter as tarefas.", "error");
    }
}

// Função edição da tarefa
window.editTask = async function editTask(taskId) {
    const description = prompt("Nova descrição:");
    const priority = prompt("Nova prioridade (Alta, Média, Baixa):");
    const status = prompt("Novo status (Pendente/ Em Andamento/ Concluída):");

    const taskData = {
        description,
        priority,
        status
    };

    try {
        const response = await fetch(`${taskServiceUrl}/${taskId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + getToken()
            },
            body: JSON.stringify(taskData)
        });

        if (response.ok) {
            showMessage("Tarefa editada com sucesso!", "success");
            getAllTasks();
        } else {
            const errorText = await response.text();
            showMessage("Erro ao editar tarefa: " + errorText, "error");
        }
    } catch (error) {
        console.error("Erro ao editar tarefa:", error);
        showMessage("Ocorreu um erro ao editar a tarefa.", "error");
    }
}

// Função para remover uma tarefa pelo ID
window.deleteTask = async function deleteTask(taskId) {
    try {
        const response = await fetch(`${taskServiceUrl}/${taskId}`, {
            method: "DELETE",
            headers: {
                "Authorization": "Bearer " + getToken()
            }
        });

        if (response.ok) {
            showMessage("Tarefa excluída com sucesso!", "success");
            getAllTasks();
        } else {
            const errorText = await response.text();
            showMessage("Erro ao excluir tarefa: " + errorText, "error");
        }
    } catch (error) {
        console.error("Erro ao excluir tarefa:", error);
        showMessage("Ocorreu um erro ao excluir a tarefa.", "error");
    }
}

// Função para obter todas as notificações do usuário autenticado
window.getNotifications = async function getNotifications() {
    try {
        const response = await fetch(`${notificationServiceUrl}/all`, {
            headers: {
                "Authorization": "Bearer " + getToken()
            }
        });
        if (response.ok) {
            const notifications = await response.json();
            const notificationsList = document.getElementById("notificationsList");
            notificationsList.innerHTML = "";

            if (notifications.length === 0) {
                notificationsList.innerHTML = "<p>Você ainda não tem notificações.</p>";
            } else {
                notifications.forEach((notification, index) => {
                    const notificationItem = document.createElement("div");
                    notificationItem.classList.add("notification-item");
                    notificationItem.innerHTML = `
            <strong>Aviso ${index + 1}:</strong> ${notification.message}
            `;
                    notificationsList.appendChild(notificationItem);
                });
            }
        } else {
            const errorText = await response.text();
            showMessage("Falha ao obter notificações: " + errorText, "error");
        }
    } catch (error) {
        console.error("Erro ao obter notificações:", error);
        showMessage("Ocorreu um erro ao obter as notificações.", "error");
    }
}

window.editField = async function editField(taskId, field) {
    let newValue = prompt(`Digite o novo valor para ${field}:`);
    if (!newValue) {
        showMessage(`O campo ${field} não pode estar vazio.`, "error");
        return;
    }

    const taskData = { [field]: newValue };

    try {
        const response = await fetch(`${taskServiceUrl}/${taskId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + getToken()
            },
            body: JSON.stringify(taskData)
        });

        if (response.ok) {
            showMessage(`Tarefa atualizada com sucesso!`, "success");
            await getAllTasks();
        } else {
            const errorText = await response.text();
            showMessage(`Erro ao atualizar ${field}: ${errorText}`, "error");
        }
    } catch (error) {
        console.error(`Erro ao atualizar ${field}:`, error);
        showMessage(`Erro ao atualizar ${field}.`, "error");
    }
}


// Função para mostrar mensagens de sucesso ou erro
function showMessage(message, type) {
    const msgDiv = document.createElement("div");
    msgDiv.classList.add("message", type);
    msgDiv.innerText = message;

    const contentDiv = document.querySelector(".content");
    contentDiv.insertBefore(msgDiv, contentDiv.firstChild);

    setTimeout(() => {
        msgDiv.remove();
    }, 5000);
}

window.onload = async function () {
    const token = getToken(); // Use getToken para recuperar o token
    const username = localStorage.getItem("username");
    if (token && username) {
        document.getElementById("usernameDisplay").innerText = username;
        showLoggedInSection();
        await getAllTasks(); // Aguarde as funções assíncronas
        await getNotifications();
    } else {
        showLoggedOutSection();
    }
};
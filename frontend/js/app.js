// URLs base dos serviços
const taskServiceUrl = "http://localhost:8081/tasks";
const notificationServiceUrl = "http://localhost:8082/notifications";
const authServiceUrl = "http://localhost:8080/auth";

let token = null;

// Função para registrar um novo usuário
async function register() {
    const username = document.getElementById("registerUsername").value;
    const password = document.getElementById("registerPassword").value;

    const registerData = {
        username,
        password
    };

    try {
        const response = await fetch(`${authServiceUrl}/register`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(registerData)
        });

        if (response.ok) {
            showMessage("Registro bem-sucedido! Por favor, faça login.", "success");
            // Limpar o formulário de registro
            document.getElementById("registerUsername").value = "";
            document.getElementById("registerPassword").value = "";
            // Alternar para a seção de login
            toggleSections();
        } else {
            const errorText = await response.text();
            showMessage("Falha no registro: " + errorText, "error");
        }
    } catch (error) {
        console.error("Erro durante o registro:", error);
        showMessage("Ocorreu um erro durante o registro.", "error");
    }
}

// Função para login
async function login() {
    const username = document.getElementById("loginUsername").value;
    const password = document.getElementById("loginPassword").value;

    const loginData = {
        username,
        password
    };

    try {
        const response = await fetch(`${authServiceUrl}/login`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(loginData)
        });

        if (response.ok) {
            const data = await response.json();
            token = data.token;
            // Armazenar token e nome de usuário no localStorage
            localStorage.setItem("token", token);
            localStorage.setItem("username", username);

            // Atualizar interface
            document.getElementById("usernameDisplay").innerText = username;
            showLoggedInSection();

            // Limpar o formulário de login
            document.getElementById("loginUsername").value = "";
            document.getElementById("loginPassword").value = "";

            // Buscar dados iniciais
            getAllTasks();
            getNotifications();
        } else {
            const errorText = await response.text();
            showMessage("Falha no login: " + errorText, "error");
        }
    } catch (error) {
        console.error("Erro durante o login:", error);
        showMessage("Ocorreu um erro durante o login.", "error");
    }
}

// Função para logout
function logout() {
    token = null;
    localStorage.removeItem("token");
    localStorage.removeItem("username");
    showLoggedOutSection();
}

// Função para alternar entre seções de registro e login
function toggleSections() {
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
async function createTask() {
    const description = document.getElementById("description").value;
    let notificationTime = document.getElementById("notificationTime").value;
    const priority = document.getElementById("priority").value;
    const status = document.getElementById("status").value;

    // Adicionar segundos se não estiverem presentes
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
                "Authorization": "Bearer " + token
            },
            body: JSON.stringify(taskData)
        });

        if (response.ok) {
            showMessage("Tarefa criada com sucesso!", "success");
            // Limpar os campos após a criação da tarefa
            document.getElementById("description").value = "";
            document.getElementById("notificationTime").value = "";
            document.getElementById("priority").value = "MEDIUM"; // Valor padrão
            document.getElementById("status").value = "PENDING"; // Valor padrão
            getAllTasks(); // Atualizar a lista de tarefas
        } else {
            const errorText = await response.text();
            showMessage("Falha ao criar tarefa: " + errorText, "error");
        }
    } catch (error) {
        console.error("Erro ao criar tarefa:", error);
        showMessage("Ocorreu um erro ao criar a tarefa.", "error");
    }
}


// Função para obter todas as tarefas do usuário autenticado
async function getAllTasks() {
    try {
        const response = await fetch(`${taskServiceUrl}/all`, {
            headers: {
                "Authorization": "Bearer " + token
            }
        });
        if (response.ok) {
            const tasks = await response.json();
            const tasksList = document.getElementById("tasksList");
            tasksList.innerHTML = ""; // Limpar a lista

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
async function editTask(taskId) {
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
                "Authorization": "Bearer " + token
            },
            body: JSON.stringify(taskData)
        });

        if (response.ok) {
            showMessage("Tarefa editada com sucesso!", "success");
            getAllTasks(); // Atualiza a lista de tarefas
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
async function deleteTask(taskId) {
    try {
        const response = await fetch(`${taskServiceUrl}/${taskId}`, {
            method: "DELETE",
            headers: {
                "Authorization": "Bearer " + token
            }
        });

        if (response.ok) {
            showMessage("Tarefa excluída com sucesso!", "success");
            getAllTasks(); // Atualiza a lista de tarefas
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
async function getNotifications() {
    try {
        const response = await fetch(`${notificationServiceUrl}/all`, {
            headers: {
                "Authorization": "Bearer " + token
            }
        });
        if (response.ok) {
            const notifications = await response.json();
            const notificationsList = document.getElementById("notificationsList");
            notificationsList.innerHTML = ""; // Limpar a lista

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

async function editField(taskId, field) {
    // Solicitar o novo valor para o campo
    let newValue = prompt(`Digite o novo valor para ${field}:`);
    if (!newValue) {
        showMessage(`O campo ${field} não pode estar vazio.`, "error");
        return;
    }

    // Criar o payload com apenas o campo atualizado
    const taskData = { [field]: newValue };

    try {
        const response = await fetch(`${taskServiceUrl}/${taskId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token
            },
            body: JSON.stringify(taskData)
        });

        if (response.ok) {
            showMessage(`Tarefa atualizada com sucesso!`, "success");
            await getAllTasks(); // Atualizar a lista de tarefas
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

    // Adicionar a mensagem no topo do conteúdo
    const contentDiv = document.querySelector(".content");
    contentDiv.insertBefore(msgDiv, contentDiv.firstChild);

    // Remover a mensagem após alguns segundos
    setTimeout(() => {
        msgDiv.remove();
    }, 5000);
}

// Ao carregar a página, verificar se o usuário já está logado
window.onload = function () {
    token = localStorage.getItem("token");
    const username = localStorage.getItem("username");
    if (token && username) {
        document.getElementById("usernameDisplay").innerText = username;
        showLoggedInSection();
        getAllTasks();
        getNotifications();
    } else {
        showLoggedOutSection();
    }
};
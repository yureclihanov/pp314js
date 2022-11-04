let requestUrl = '/admin/api/users'

//------ Table users ------------------------
function refreshData() {
    fetch(requestUrl)
        .then(response => response.json())
        .then(result => refreshTable(result))

    function refreshTable(users) {
        let tBody = ''
        $('#usersTable').find('tr').remove();
        $.each(users, function (key, object) {
            let roles = ''
            $.each(object.roles, function (k, o) {
                roles += o.role + ' '
            })
            tBody += ('<tr>');
            tBody += ('<td>' + object.id + '</td>');
            tBody += ('<td>' + object.name + '</td>');
            tBody += ('<td>' + object.lastName + '</td>');
            tBody += ('<td>' + object.age + '</td>');
            tBody += ('<td>' + object.email + '</td>');
            tBody += ('<td>' + roles.replaceAll('ROLE_', ' ') + '</td>');
            tBody += ('<td><button type="button" onclick="editModal(' + object.id + ')" ' +
                'class="btn btn-primary">Edit</button></td>');
            tBody += ('<td><button type="button" onclick="deleteModal(' + object.id + ')" ' +
                'class="btn btn-danger">Delete</button></td>');
            tBody += ('<tr>');
        });
        $('#usersTable').html(tBody);
    }
}

//------ Create new user ------------------------

async function addNewUser() {
    let id1
    let authority1
    let role1

    if ($('#newRoles').val().toString() === '1') {
        id1 = '1'
        role1 = 'ROLE_USER'
        authority1 = 'ROLE_USER'
    } else {
        id1 = '2'
        role1 = 'ROLE_ADMIN'
        authority1 = 'ROLE_ADMIN'
    }

    await fetch(requestUrl, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },

        body: JSON.stringify({
            name: $('#newName').val(),
            lastName: $('#newLastName').val(),
            password: $('#newPassword').val(),
            age: $('#newAge').val(),
            email: $('#newUserEmail').val(),
            roles: [{
                id: id1,
                role: role1,
                authority: authority1
            }]
        })
    })
        .then((r) => {
            if (r.ok) {
                refreshData()
            }
        })
}

//------ Modal update user ------------------------
function editModal(id) {
    fetch(requestUrl + '/' + id)
        .then(response => response.json())
        .then(result => fillFields(result))

    function fillFields(user) {
        $('#edId').val(user.id);
        $('#edPassword').val(user.password);
        $('#edName').val(user.name);
        $('#edLastName').val(user.lastName);
        $('#edAge').val(user.age);
        $('#edUserEmail').val(user.email);
        $('#editModal').modal()
        $('#edit').attr('onclick', 'editUser(' + user.id + ')')
    }
}

function editUser(id) {
    let id2
    let authority2
    let role2

    if ($('#rolesEdit').val().toString() === '1') {
        id2 = '1'
        role2 = 'ROLE_USER'
        authority2 = 'ROLE_USER'
    } else {
        id2 = '2'
        role2 = 'ROLE_ADMIN'
        authority2 = 'ROLE_ADMIN'
    }
    fetch(requestUrl + '/' + id,
        {
            method: "PUT",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(
                {
                    id: document.getElementById('edId').value,
                    name: document.getElementById('edName').value,
                    lastName: document.getElementById('edLastName').value,
                    age: document.getElementById('edAge').value,
                    email: document.getElementById('edUserEmail').value,
                    password: document.getElementById('edPassword').value,
                    roles: [{
                        id: id2,
                        role: role2,
                        authority: authority2
                    }]
                })
        }).then((re) => {
        $('#editModal').modal("hide")
        refreshData()
    })
}

//------ Modal delete user ------------------------
function deleteModal(id) {
    fetch(requestUrl + '/' + id)
        .then(response => response.json())
        .then(result => fillFields(result))

    function fillFields(user) {
        $('#delId').val(user.id);
        $('#delName').val(user.name);
        $('#delLastName').val(user.lastName);
        $('#delAge').val(user.age);
        $('#delUserEmail').val(user.email);
        $('#delete').attr('onclick', 'deleteUser(' + user.id + ')')
        $('#deleteModalHtml').modal()
    }
}

function deleteUser(id) {
    fetch(requestUrl + '/' + id, {
        method: 'DELETE'
    }).then(() => {
        $('#deleteModalHtml').modal('hide')
        refreshData();
    })
}


refreshData()
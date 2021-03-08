# teacher-helper-b
The Back-end api for teacher helper, which is my first Standalone Project using Spring.

<b>Postman Link</b>: https://www.getpostman.com/collections/715292f267cb10709143

<b>Production Back-end Link</b>: https://teacher-helper-b-hero.herokuapp.com/
If you desire to test the API, please uuse the Postman Link and change <b>Localhost</b> url to <b>Back-end</b> link

Global Variables for Postman: 
<img src="https://i.postimg.cc/sXDXQNwK/Screenshot-1.png"/>

Postman Pre Request Script:
The Pre Request script is for the purpose of Login and change the <b>Global Authorization</b> token
``
<pre>
pm.sendRequest({
    url: pm.globals.get("authorization_url"),
    method: 'Post',
    header: {
        'content-type': 'application/json'
    },
    body: {
        mode: 'raw',
        raw: JSON.stringify({ email: pm.globals.get("email"), password: pm.globals.get("password") })
    }
}, function (err, response) {
    var token = response.headers.filter(header => {
        return header.key === "Authorization";
    });
    console.log(token[0]);
    pm.globals.set('Authorization', token[0].value);
});
</pre>
``

<b>Still in development</b>

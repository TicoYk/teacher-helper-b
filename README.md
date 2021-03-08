<h2>teacher-helper-b</h2>
<div text-align:right>
</div>
<p>The Back-end api for teacher helper, which is my first Standalone Project using Spring.<p>
<p>
    <b>Postman Link</b>: https://www.getpostman.com/collections/715292f267cb10709143
    </br>
    <b>Production Back-end Link</b>: https://teacher-helper-b-hero.herokuapp.com/
    </br>
    <b>Obs: Will use a proper DNS only when the application is completed.(Front and Back)</b>
</p>
<p>
If you desire to test the API, please use the Postman Link and change <b>localhost:8080</b> url to the <b>Production Back-end Link</b>
</p>
Global Variables for Postman: 
<img src="https://i.postimg.cc/sXDXQNwK/Screenshot-1.png"/>

<p>
Postman Pre Request Script:
</br>
    The Pre Request script is for the purpose of Login and change the <b>Header Authorization</b> token
</p>
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


<b>*Still in development</b>

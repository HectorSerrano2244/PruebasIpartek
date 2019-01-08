<link href="https://getbootstrap.com/docs/4.2/examples/navbar-fixed/navbar-top-fixed.css" rel="stylesheet">
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
	<a class="navbar-brand" href="#">APP POLICÍA</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarCollapse" aria-controls="navbarCollapse"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse text-white" id="navbarCollapse">
		<ul class="navbar-nav">
			<li class="list-unlisted text-white">${usuarioLogueado.email}</li>
		</ul>
	</div>
</nav>
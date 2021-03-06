import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

// Componentes
import { HomeComponent } from './components/home/home.component';
import { JuegoComponent } from './components/juego/juego.component';
import { AboutComponent } from './components/about/about.component';
import { Error404Component } from './components/error404/error404.component';
import { SaludoComponent } from './components/saludo/saludo.component';
import { PaginaDirectivaComponent } from './components/pagina-directiva/pagina-directiva.component';
import { FlujoComponent } from './components/flujo/flujo.component';
import { PipesComponent } from './components/pipes/pipes.component';
import { ArraysComponent } from './components/arrays/arrays.component';
import { PaginaComparadorComponent } from './components/pagina-comparador/pagina-comparador.component';
import { PaginaConcesionarioComponent } from './components/pagina-concesionario/pagina-concesionario.component';

const routes: Routes = [
  {path: 'home', component: HomeComponent },
  {path: 'about', component: AboutComponent },
  {path: 'about/:repeticiones', component: AboutComponent },
  {path: 'juego', component: JuegoComponent },
  {path: '404', component: Error404Component },
  {path: 'directivas', component: PaginaDirectivaComponent },
  {path: 'saludo/:nombre/:apellido1/:apellido2', component: SaludoComponent },
  {path: 'flujo', component: FlujoComponent },
  {path: 'pipes', component: PipesComponent },
  {path: 'arrays', component: ArraysComponent },
  {path: 'comparador', component: PaginaComparadorComponent },
  {path: 'concesionario', component: PaginaConcesionarioComponent },
  {path: '**', pathMatch: 'full', redirectTo: '404'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

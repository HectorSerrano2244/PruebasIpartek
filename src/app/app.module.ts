import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

// Nuestros componentes
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { JuegoComponent } from './components/juego/juego.component';
import { HomeComponent } from './components/home/home.component';
import { AboutComponent } from './components/about/about.component';
import { Error404Component } from './components/error404/error404.component';
import { SaludoComponent } from './components/saludo/saludo.component';
import { PaginaDirectivaComponent } from './components/pagina-directiva/pagina-directiva.component';
import { Directiva1Directive } from './directives/directiva1.directive';
import { CountdownDirective } from './directives/countdown.directive';
import { FlujoComponent } from './components/flujo/flujo.component';
import { NombreDirective } from './directives/nombre.directive';
import { PipesComponent } from './components/pipes/pipes.component';
import { MonedaPipe } from './pipes/moneda.pipe';
import { TrimPipe } from './pipes/trim.pipe';

@NgModule({
  declarations: [
    AppComponent,
    JuegoComponent,
    HomeComponent,
    AboutComponent,
    Error404Component,
    SaludoComponent,
    PaginaDirectivaComponent,
    Directiva1Directive,
    CountdownDirective,
    FlujoComponent,
    NombreDirective,
    PipesComponent,
    MonedaPipe,
    TrimPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {Sidenav} from './components/sidenav/sidenav';
import {Toolbar} from './components/toolbar/toolbar';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Sidenav, Toolbar],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected readonly title = signal('Metis');
}

import {NgModule} from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  MatButtonModule, MatNativeDateModule, MatIconModule, MatSidenavModule,
  MatListModule, MatToolbarModule, MatCardModule, MatInputModule, MatSelectModule
} from '@angular/material';

@NgModule({
  // tslint:disable-next-line:max-line-length
  imports: [CommonModule, MatButtonModule, MatToolbarModule, MatNativeDateModule, MatIconModule, MatSidenavModule, MatListModule, MatCardModule, MatInputModule, MatSelectModule],
  // tslint:disable-next-line:max-line-length
  exports: [CommonModule, MatButtonModule, MatToolbarModule, MatNativeDateModule, MatIconModule, MatSidenavModule, MatListModule, MatCardModule, MatInputModule, MatSelectModule],
})
export class CustomMaterialModule { }

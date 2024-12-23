import { NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-search-results',
  standalone:true,
  imports: [NgFor,NgIf],
  templateUrl: './search-results.component.html',
  styleUrl: './search-results.component.css'
})
export class SearchResultsComponent implements OnInit{
  trains: any[] = [];
  trainClass: string = localStorage.getItem('searchResultsClass') || '';
  price: any

  ngOnInit(): void {
    const storedResults = localStorage.getItem('searchResults');
    if (storedResults) {
      this.trains = JSON.parse(storedResults);
    }
  }
}

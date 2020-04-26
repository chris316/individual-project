import { Component, Injectable, OnInit,Input } from '@angular/core';
import {NgForm} from '@angular/forms';

import { User }    from '../user';
import { AuthService } from '../auth-service';
import { SearchType } from '../searchType';
import{FansService} from '../fans-service';


@Component({
  selector: 'app-search-home',
  templateUrl: './search-home.component.html',
  styleUrls: ['./search-home.component.css']
})
export class SearchHomeComponent implements OnInit{

  @Input() list: string[]=["Mechanical","HVAC Fans"];

      // two way binding for input text
      inputItem = '';

          // enable or disable visiblility of dropdown
    listHidden = true;

    showError = false;
    selectedIndex = -1;

        // the list to be shown after filtering
        filteredList: string[] = [];


  mechanic:SearchType={
    typing:""
  };

  types:string[]=["Mechanical","HVAC Fans"];


  constructor(public authService:AuthService, public fansService:FansService){
    
    
  }

  ngOnInit() {
    this.filteredList = this.list;
}

    // modifies the filtered list as per input
    getFilteredList() {
      this.listHidden = false;
      if (!this.listHidden && this.inputItem !== undefined) {
          this.filteredList = this.list.filter((item) =>  item.toLowerCase().startsWith(this.inputItem.toLowerCase()));
  }
}

    // select highlighted item when enter is pressed or any item that is clicked
    selectItem(ind) {
      this.inputItem = this.filteredList[ind];
      this.listHidden = true;
      this.selectedIndex = ind;
  }

      // navigate through the list of items
      onKeyPress(event) {
        if (!this.listHidden) {
            if (event.key === 'Escape') {
                this.selectedIndex = -1;
                this.toggleListDisplay(0);
            }else if (event.key === 'Enter') {
                this.toggleListDisplay(0);
            }else if (event.key === 'ArrowDown') {
                this.listHidden = false;
                this.selectedIndex = (this.selectedIndex + 1) % this.filteredList.length;
                if (this.filteredList.length > 0 && !this.listHidden) {
                    document.getElementsByTagName('list-item')[this.selectedIndex].scrollIntoView();
                }
            } else if (event.key === 'ArrowUp') {
                this.listHidden = false;
                if (this.selectedIndex <= 0) {
                    this.selectedIndex = this.filteredList.length;
                }
                this.selectedIndex = (this.selectedIndex - 1) % this.filteredList.length;
                if (this.filteredList.length > 0 && !this.listHidden) {
                document.getElementsByTagName('list-item')[this.selectedIndex].scrollIntoView();
                }
            }
        }
    }

        // show or hide the dropdown list when input is focused or moves out of focus
        toggleListDisplay(sender: number) {
          if (sender === 1) {
              this.listHidden = false;
              this.getFilteredList();
          } else {
              // helps to select item by clicking
              setTimeout(() => {
                  this.selectItem(this.selectedIndex);
                  this.listHidden = true;
                  if (!this.list.includes(this.inputItem)) {
                      this.showError = true;
                      this.filteredList = this.list;
                  } else {
                      this.showError = false;
                  }
              }, 500);
          }
      }

  onSubmit(form: NgForm) { 
    this.authService.goToFans();
  }

    goToProjects(){
        this.fansService.loadProjectFans();
        setTimeout(()=>{
          this.authService.goToProjects();
        },1500)
  }

  logout(){
    this.authService.logout();
  }

}
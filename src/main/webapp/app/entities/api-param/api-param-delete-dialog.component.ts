import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IApiParam } from 'app/shared/model/api-param.model';
import { ApiParamService } from './api-param.service';

@Component({
    selector: 'jhi-api-param-delete-dialog',
    templateUrl: './api-param-delete-dialog.component.html'
})
export class ApiParamDeleteDialogComponent {
    apiParam: IApiParam;

    constructor(protected apiParamService: ApiParamService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.apiParamService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'apiParamListModification',
                content: 'Deleted an apiParam'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-api-param-delete-popup',
    template: ''
})
export class ApiParamDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ apiParam }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ApiParamDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.apiParam = apiParam;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}

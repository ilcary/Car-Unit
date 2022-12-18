import { Injectable } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { FileHandle } from '../models/file_handle.model';
import { IDealCar } from '../models/iDealCar';

@Injectable({
  providedIn: 'root'
})
export class ImageProcessingService {

  constructor(private sanitizer: DomSanitizer) { }

  public createImages(dealCar: any) {
    const imgs: any[] = dealCar.productImages
    ;
    const IDealCarToFileHandle: FileHandle[] = [];

    for (let i = 0; i < imgs.length; i++) {
      const productImage = imgs[i];

      const imageBlob = this.dataURItoBlob(productImage.picByte, productImage.type)

      const imageFile = new File([imageBlob], productImage.name, { type: productImage.type });

      const finalFileHandle: FileHandle = {
        file: imageFile,
        url: this.sanitizer.bypassSecurityTrustUrl(window.URL.createObjectURL(imageFile))
      };

      IDealCarToFileHandle.push(finalFileHandle);
    }

    dealCar.productImages
    = IDealCarToFileHandle
    return dealCar;

  }

  public dataURItoBlob(picBytes: string, imageType: any) {
    const byteString = window.atob(picBytes);
    const arrayBuffer = new ArrayBuffer(byteString.length);
    const int8Array = new Uint8Array(arrayBuffer);

    for (let i = 0; i < byteString.length; i++) {
      int8Array[i] = byteString.charCodeAt(i)
    }
    const blob_ = new Blob([int8Array], { type: imageType })
    return blob_;

  }


}

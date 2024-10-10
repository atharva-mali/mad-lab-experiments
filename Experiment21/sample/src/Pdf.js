import React, { useState } from 'react';
import { Document, Page, pdfjs } from 'react-pdf';
import 'react-pdf/dist/esm/Page/AnnotationLayer.css';

// Set the worker source to use pdf.worker.js from a CDN
pdfjs.GlobalWorkerOptions.workerSrc = `https://cdnjs.cloudflare.com/ajax/libs/pdf.js/${pdfjs.version}/pdf.worker.mjs`;

const Pdf = () => {
  // Replace with a working PDF URL or a CORS proxy if necessary
  const url = 'https://cors-anywhere.herokuapp.com/http://www.pdf995.com/samples/pdf.pdf';
  // You can also try another PDF URL:
  // const url = 'https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf';

  const [numPages, setNumPages] = useState(null);
  const [pageNumber, setPageNumber] = useState(1);

  // Prevent right-click on the screen
  React.useEffect(() => {
    document.addEventListener('contextmenu', (event) => {
      event.preventDefault();
    });
    return () => {
      document.removeEventListener('contextmenu', (event) => {
        event.preventDefault();
      });
    };
  }, []);

  // Handle successful document load
  function onDocumentLoadSuccess({ numPages }) {
    setNumPages(numPages);
    setPageNumber(1);
  }

  function changePage(offset) {
    setPageNumber((prevPageNumber) => prevPageNumber + offset);
  }

  function previousPage() {
    changePage(-1);
  }

  function nextPage() {
    changePage(1);
  }

  return (
    <div className="main">
      <Document file={url} onLoadSuccess={onDocumentLoadSuccess} onLoadError={(error) => console.error('Error loading document:', error)}>
        <Page pageNumber={pageNumber} />
      </Document>
      <div>
        <div className="pagec">
          Page {pageNumber || (numPages ? 1 : '--')} of {numPages || '--'}
        </div>
        <div className="buttonc">
          <button
            type="button"
            disabled={pageNumber <= 1}
            onClick={previousPage}
            className="Pre"
          >
            Previous
          </button>
          <button
            type="button"
            disabled={pageNumber >= numPages}
            onClick={nextPage}
          >
            Next
          </button>
        </div>
      </div>
    </div>
  );
};

export default Pdf;

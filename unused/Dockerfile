FROM python:latest
RUN echo 'DOCKER IS HERE'
RUN echo 'python --version'
WORKDIR C:/Users/lpzdb/psychopyTestSuite/testProject/
RUN dir
COPY . .
RUN dir
RUN pip install -r requirements.txt
RUN python setup.py develop
EXPOSE 8080
CMD ["pytest", "--cov-report xml:coveragePy3.xml --cov=proj tests"]


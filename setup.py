from setuptools import setup, find_packages

with open("README.md", "r") as fh:
    long_description = fh.read()

setup(name="proj",
      version="0.0.1",

      # metadata to display on PyPI
      author="DB",
      author_email="db@example.com",
      description="A small example package",
      long_description=long_description,
      url="https://github.com/pypa/sampleproject",
      packages=find_packages(),
      classifiers=[
          "Programming Language :: Python :: 3.6",
          "License :: OSI Approved :: MIT License",
          "Operating System :: OS Independent",
      ],
      )
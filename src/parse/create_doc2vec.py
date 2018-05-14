from gensim.corpora.wikicorpus import WikiCorpus
from gensim.models.doc2vec import Doc2Vec, TaggedDocument
from pprint import pprint
import multiprocessing
import os


class TaggedWikiDocument(object):
    def __init__(self, directory):
        self.directory = directory
    def __iter__(self):
        for subdir,dirs,files in os.walk(self.directory):
            for file in files:

                with open(os.path.join(subdir,file),"r", encoding="utf8") as f:
                    lines = f.readlines()
                    title = ''
                    content = ''
                    next_line_is_title = False
                    for line in lines:
                        if next_line_is_title:
                            title = line
                            next_line_is_title = False
                        elif line.startswith("<doc"):
                            next_line_is_title = True
                            content = ''
                        elif line.startswith("</doc"):
                            yield TaggedDocument(content.split(), [title])
                        else:
                            content += " "+line






documents = TaggedWikiDocument("./text")
cores = multiprocessing.cpu_count()

model = Doc2Vec(dm=1, dm_mean=1, size=200, window=8, min_count=19, iter =10, workers=cores)

model.build_vocab(documents)
model.train(documents, epochs=model.iter, total_examples=model.corpus_count)

model.save("wikid2v.dat")
